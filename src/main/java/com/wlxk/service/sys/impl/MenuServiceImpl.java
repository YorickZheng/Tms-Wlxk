package com.wlxk.service.sys.impl;

import com.google.common.base.Strings;
import com.google.common.primitives.Ints;
import com.wlxk.controller.sys.vo.menu.AddMenuVo;
import com.wlxk.controller.sys.vo.menu.DisuseMenuVo;
import com.wlxk.controller.sys.vo.menu.QueryMenuVo;
import com.wlxk.controller.sys.vo.menu.UpdateMenuVo;
import com.wlxk.domain.sys.Menu;
import com.wlxk.domain.sys.MenuOperationRecord;
import com.wlxk.repository.sys.MenuRepository;
import com.wlxk.service.sys.MenuOperationRecordService;
import com.wlxk.service.sys.MenuService;
import com.wlxk.support.exception.TmsDataValidationException;
import com.wlxk.support.util.CommonProperty;
import com.wlxk.support.util.ResultsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by malin on 2016/7/28.
 */
@Service
public class MenuServiceImpl implements MenuService {

    private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired(required = false)
    private MenuRepository repository;
    @Autowired(required = false)
    private MenuOperationRecordService operationRecordService;

    @Override
    public Menu save(Menu menu) {
        return repository.save(menu);
    }

    @Override
    public Iterable<Menu> save(List<Menu> list) {
        return repository.save(list);
    }

    @Override
    public Menu getOneById(String id) {
        return repository.findOne(id);
    }

    @Override
    public List<Menu> getListById(List<String> ids) {
        return repository.findByIdIn(ids);
    }

    @Override
    public List<Menu> getListByParentMenuId(String parentMenuId) {
        return repository.findByParentMenuId(parentMenuId);
    }

    @Override
    public Map add(AddMenuVo vo) {
        try {
            logger.info("1. 数据校验");
            addByDataValidation(vo);

            logger.info("2. 新增菜单");
            Menu menu = save(vo.getMenu());

            logger.info("3. 新增操作记录");
            operationRecordService.save(MenuOperationRecord.newInstance(menu.getId(), vo.getOperationById(), vo.getOperationByName(), vo.getDescription()));
        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error("新增失败!", e);
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("新增成功!");
    }

    private void addByDataValidation(AddMenuVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("请求主体对象不能为空!");
        }
        if (Objects.isNull(vo.getMenu())) {
            throw new TmsDataValidationException("菜单对象不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getOperationById())) {
            throw new TmsDataValidationException("操作人ID不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getOperationByName())) {
            throw new TmsDataValidationException("操作人名称不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getDescription())) {
            throw new TmsDataValidationException("操作说明不能为空!");
        }
    }

    @Override
    public Map disuse(DisuseMenuVo vo) {
        try {
            logger.info("1. 数据校验");
            disuseByDataValidation(vo);

            logger.info("2. " + (vo.getCommand() == CommonProperty.DisuseCommand.DISUSE ? "作废" : "取消作废") + "菜单");
            String menuId = vo.getBusinessId();
            Menu menu = getOneById(menuId);
            if (CommonProperty.DisuseCommand.DISUSE == vo.getCommand()) {
                menu.setDeleteFlag(CommonProperty.DeleteFlag.DELETE_OFF);
            } else if (CommonProperty.DisuseCommand.DISUSE_CANCEL == vo.getCommand()) {
                menu.setDeleteFlag(CommonProperty.DeleteFlag.DELETE_ON);
            }
            menu.setModifyById(vo.getOperationById());
            menu.setModifyByName(vo.getOperationByName());
            menu.setModifyByDate(Calendar.getInstance().getTime());
            save(menu);

            logger.info("3. 新增操作记录");
            operationRecordService.save(MenuOperationRecord.newInstance(vo.getBusinessId(), vo.getOperationById(), vo.getOperationByName(), vo.getDescription()));
        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error((vo.getCommand() == CommonProperty.DisuseCommand.DISUSE ? "作废" : "取消作废") + "失败!", e);
            throw e;
        }
        return ResultsUtil.getSuccessResultMap((vo.getCommand() == CommonProperty.DisuseCommand.DISUSE ? "作废" : "取消作废") + "成功!");
    }

    private void disuseByDataValidation(DisuseMenuVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("请求主体对象不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getBusinessId())) {
            throw new TmsDataValidationException("业务ID不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getOperationById())) {
            throw new TmsDataValidationException("操作人ID不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getOperationByName())) {
            throw new TmsDataValidationException("操作人名称不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getDescription())) {
            throw new TmsDataValidationException("操作说明不能为空!");
        }
        if (Objects.isNull(vo.getCommand())) {
            throw new TmsDataValidationException("命令不能为空!");
        }
        if (!Ints.contains(CommonProperty.DisuseCommand.COMMANDS, vo.getCommand())) {
            throw new TmsDataValidationException("命令无效!");
        }
    }

    @Override
    public Map update(UpdateMenuVo vo) {
        try {
            logger.info("1. 数据校验");
            updateByDataValidation(vo);

            logger.info("2. 更新数据");
            Menu menu = vo.getMenu();
            menu.setModifyById(vo.getOperationById());
            menu.setModifyByName(vo.getOperationByName());
            menu.setModifyByDate(Calendar.getInstance().getTime());
            save(menu);

            logger.info("3. 新增操作记录");
            operationRecordService.save(MenuOperationRecord.newInstance(menu.getId(), vo.getOperationById(), vo.getOperationByName(), vo.getDescription()));
        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error("更新失败!", e);
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("更新成功!");
    }

    private void updateByDataValidation(UpdateMenuVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("请求主体对象不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getOperationById())) {
            throw new TmsDataValidationException("操作人ID不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getOperationByName())) {
            throw new TmsDataValidationException("操作人名称不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getDescription())) {
            throw new TmsDataValidationException("操作说明不能为空!");
        }
        if (Objects.isNull(vo.getMenu())) {
            throw new TmsDataValidationException("菜单对象不能为空!");
        }
    }

    @Override
    public Map queryMenu(QueryMenuVo vo) {
        try {
            logger.info("1. 数据校验");
            queryMenuByDataValidation(vo);

            logger.info("2. 查询菜单");
            if (vo.getCommand() == CommonProperty.MenuQueryCommand.QUERY_BY_ID) {
                return ResultsUtil.getSuccessResultMap(getOneById(vo.getMenuId()));
            } else if (vo.getCommand() == CommonProperty.MenuQueryCommand.QUERY_BY_PARENT_ID) {
                return ResultsUtil.getSuccessResultMap(getListByParentMenuId(vo.getParentMenuId()));
            }
        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error("查询失败!", e);
            throw e;
        }
        return null;
    }

    private void queryMenuByDataValidation(QueryMenuVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("请求主体对象不能为空!");
        }
        if (Objects.isNull(vo.getCommand())) {
            throw new TmsDataValidationException("命令不能为空!");
        }
        if (!Ints.contains(CommonProperty.MenuQueryCommand.COMMANDS, vo.getCommand())) {
            throw new TmsDataValidationException("命令无效!");
        }
        if (CommonProperty.MenuQueryCommand.QUERY_BY_ID == vo.getCommand()) {
            if (Strings.isNullOrEmpty(vo.getMenuId())) {
                throw new TmsDataValidationException("菜单ID不能为空!");
            }
        }
        if (CommonProperty.MenuQueryCommand.QUERY_BY_PARENT_ID == vo.getCommand()) {
            if (Strings.isNullOrEmpty(vo.getParentMenuId())) {
                throw new TmsDataValidationException("菜单父ID不能为空!");
            }
        }
    }
}
