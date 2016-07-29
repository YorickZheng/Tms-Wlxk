package com.wlxk.service.sys.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import com.wlxk.controller.sys.vo.role.*;
import com.wlxk.domain.sys.Menu;
import com.wlxk.domain.sys.Role;
import com.wlxk.domain.sys.RoleMenu;
import com.wlxk.domain.sys.RoleOperationRecord;
import com.wlxk.repository.sys.RoleRepository;
import com.wlxk.repository.sys.specs.RoleSpecs;
import com.wlxk.service.sys.MenuService;
import com.wlxk.service.sys.RoleMenuService;
import com.wlxk.service.sys.RoleOperationRecordService;
import com.wlxk.service.sys.RoleService;
import com.wlxk.support.exception.TmsDataValidationException;
import com.wlxk.support.util.CommonProperty;
import com.wlxk.support.util.PageUtil;
import com.wlxk.support.util.ResultsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by malin on 2016/7/28.
 */
@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired(required = false)
    private RoleRepository repository;
    @Autowired(required = false)
    private RoleMenuService roleMenuService;
    @Autowired(required = false)
    private MenuService menuService;
    @Autowired(required = false)
    private RoleOperationRecordService operationRecordService;


    @Override
    public Role save(Role role) {
        return repository.save(role);
    }

    @Override
    public Iterable<Role> save(List<Role> list) {
        return repository.save(list);
    }

    @Override
    public Role getOneById(String id) {
        return repository.findOne(id);
    }

    @Override
    public List<Role> getListById(List<String> idList) {
        return repository.findByIdIn(idList);
    }

    @Override
    public Map add(AddRoleVo vo) {
        try {
            logger.info("1. 数据校验");
            addRoleByDataValidation(vo);

            logger.info("2. 添加角色");
            Role role = save(vo.getRole());

            logger.info("3. 添加角色菜单关联");
            List<RoleMenu> roleMenuList = vo.getRoleMenuList();
            roleMenuList.forEach(roleMenu -> {
                roleMenu.setRoleId(role.getId());
            });
            roleMenuService.save(roleMenuList);

            logger.info("4. 添加操作记录");
            operationRecordService.save(RoleOperationRecord.newInstance(role.getId(), vo.getOperationById(), vo.getOperationByName(), vo.getDescription()));
        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error("新增失败!", e);
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("新增成功!");
    }

    private void addRoleByDataValidation(AddRoleVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("请求主体对象为空!");
        }
        if (Objects.isNull(vo.getRole())) {
            throw new TmsDataValidationException("角色对象为空!");
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
    public Map disuse(DisuseRoleVo vo) {
        try {
            logger.info("1. 数据校验");
            disuseRoleByDataValidation(vo);

            logger.info("2. " + (vo.getCommand() == CommonProperty.DisuseCommand.DISUSE ? "作废" : "取消作废") + "角色");
            Role role = getOneById(vo.getBusinessId());
            if (CommonProperty.DisuseCommand.DISUSE == vo.getCommand()) {
                role.setDeleteFlag(CommonProperty.DeleteFlag.DELETE_OFF);
            } else if (CommonProperty.DisuseCommand.DISUSE_CANCEL == vo.getCommand()) {
                role.setDeleteFlag(CommonProperty.DeleteFlag.DELETE_ON);
            }

            role.setModifyById(vo.getOperationById());
            role.setModifyByName(vo.getOperationByName());
            role.setModifyByDate(Calendar.getInstance().getTime());

            logger.info("3. " + (vo.getCommand() == CommonProperty.DisuseCommand.DISUSE ? "作废" : "取消作废") + "角色菜单关联");
            List<RoleMenu> roleMenuList = roleMenuService.getListByRoleId(role.getId());
            roleMenuList.forEach(roleMenu -> {
                if (CommonProperty.DisuseCommand.DISUSE == vo.getCommand()) {
                    roleMenu.setDeleteFlag(CommonProperty.DeleteFlag.DELETE_OFF);
                } else if (CommonProperty.DisuseCommand.DISUSE_CANCEL == vo.getCommand()) {
                    roleMenu.setDeleteFlag(CommonProperty.DeleteFlag.DELETE_ON);
                }
                roleMenu.setModifyById(vo.getOperationById());
                roleMenu.setModifyByName(vo.getOperationByName());
                roleMenu.setModifyByDate(Calendar.getInstance().getTime());
            });
            roleMenuService.save(roleMenuList);

            logger.info("4. 添加操作记录");
            operationRecordService.save(RoleOperationRecord.newInstance(vo.getBusinessId(), vo.getOperationById(), vo.getOperationByName(), vo.getDescription()));
        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error((vo.getCommand() == CommonProperty.DisuseCommand.DISUSE ? "作废" : "取消作废") + "失败!", e);
            throw e;
        }
        return ResultsUtil.getSuccessResultMap((vo.getCommand() == CommonProperty.DisuseCommand.DISUSE ? "作废" : "取消作废") + "成功!");
    }

    private void disuseRoleByDataValidation(DisuseRoleVo vo) {
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
    public Map update(UpdateRoleVo vo) {
        try {
            logger.info("1. 数据校验");
            updateRoleByDataValidation(vo);

            logger.info("2. 更新角色");
            Role role = vo.getRole();
            role.setModifyById(vo.getOperationById());
            role.setModifyByName(vo.getOperationByName());
            role.setModifyByDate(Calendar.getInstance().getTime());
            save(role);

            logger.info("3. 添加操作记录");
            operationRecordService.save(RoleOperationRecord.newInstance(role.getId(), vo.getOperationById(), vo.getOperationByName(), vo.getDescription()));
        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error("更新失败!", e);
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("更新成功!");
    }

    private void updateRoleByDataValidation(UpdateRoleVo vo) {
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
        if (Objects.isNull(vo.getRole())) {
            throw new TmsDataValidationException("角色对象不能为空!");
        }
    }

    @Override
    public Page<Role> getPage(Pageable pageable, Map<String, Object> params) {
        return repository.findAll(RoleSpecs.rolePageSpecs(params), pageable);
    }

    @Override
    public Map getPageView(QueryRoleVo vo) {
        try {
            logger.info("1. 数据校验");
            getPageViewByDataValidation(vo);

            logger.info("2. 查询主表");
            PageRequest request = new PageRequest(vo.getPage(), vo.getSize(), new Sort(vo.getDirection(), vo.getSortList()));
            Page<Role> page = getPage(request, vo.getParams());

            logger.info("3. 查询子表");
            List<RoleView> content = Lists.newArrayList();
            page.getContent().forEach(role -> {
                String roleId = role.getId();
                List<RoleMenu> roleMenuList = roleMenuService.getListByRoleId(roleId);
                List<String> menuIds = roleMenuList.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
                List<Menu> menuList = menuService.getListById(menuIds);
                content.add(RoleView.newInstance(role, menuList));
            });

            logger.info("4. 组装数据返回");
            return ResultsUtil.getSuccessResultMap(PageUtil.newInstancePage(page, content));

        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error("查询失败!", e);
            throw e;
        }
    }

    private void getPageViewByDataValidation(QueryRoleVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("请求主体对象不能为空!");
        }
        if (Objects.isNull(vo.getParams())) {
            throw new TmsDataValidationException("查询参数列表不能为空!");
        }
        if (Objects.isNull(vo.getPage())) {
            throw new TmsDataValidationException("起始页不能为空!");
        }
        if (Objects.isNull(vo.getSize())) {
            throw new TmsDataValidationException("每页数量不能为空!");
        }
        if (Objects.isNull(vo.getDirection())) {
            throw new TmsDataValidationException("排序方式不能为空!");
        }
        if (Objects.isNull(vo.getSortList())) {
            throw new TmsDataValidationException("排序集合不能为空!");
        }
    }
}
