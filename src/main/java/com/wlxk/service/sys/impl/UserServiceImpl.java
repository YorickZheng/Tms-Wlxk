package com.wlxk.service.sys.impl;

import com.fasterxml.jackson.databind.util.ArrayIterator;
import com.google.common.base.Strings;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import com.wlxk.controller.sys.vo.user.*;
import com.wlxk.domain.sys.Role;
import com.wlxk.domain.sys.User;
import com.wlxk.domain.sys.UserOperationRecord;
import com.wlxk.domain.sys.UserRole;
import com.wlxk.repository.sys.UserRepository;
import com.wlxk.service.sys.RoleService;
import com.wlxk.service.sys.UserOperationRecordService;
import com.wlxk.service.sys.UserRoleService;
import com.wlxk.service.sys.UserService;
import com.wlxk.support.exception.TmsDataValidationException;
import com.wlxk.support.util.CommonProperty;
import com.wlxk.support.util.PageUtil;
import com.wlxk.support.util.ResultsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 用户服务层实现
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/22
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired(required = false)
    private UserRepository repository;
    // 用户角色关联 service
    @Autowired(required = false)
    private UserRoleService userRoleService;
    // 角色 service
    @Autowired(required = false)
    private RoleService roleService;
    // 用户操作记录 service
    @Autowired(required = false)
    private UserOperationRecordService operationRecordService;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User getOneById(String id) {
        return repository.findOne(id);
    }

    @Override
    public Map add(AddUserVo vo) {
        try {
            logger.info("1. 数据校验");
            addByDataValidation(vo);

            logger.info("2. 新增用户");
            User user = save(vo.getUser());

            logger.info("3. 新增用户角色关联");
            List<UserRole> userRoleList = vo.getUserRoleList();
            userRoleList.forEach(userRole -> {
                userRole.setUserId(user.getId());
            });
            userRoleService.save(vo.getUserRoleList());

            logger.info("3. 添加操作记录");
            operationRecordService.save(UserOperationRecord.newInstance(user.getId(), vo.getOperationById(), vo.getOperationByName(), vo.getDescription()));
        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error("新增失败!", e);
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("新增成功!");
    }

    private void addByDataValidation(AddUserVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("请求主体对象不能为空!");
        }
        if (Objects.isNull(vo.getUser())) {
            throw new TmsDataValidationException("用户对象不能为空!");
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
    public Map disuse(DisuseUserVo vo) {
        try {
            logger.info("1. 数据校验");
            disuseByDataValidation(vo);

            logger.info("2. " + (vo.getCommand() == 1 ? "作废" : "取消作废") + "用户");
            User user = getOneById(vo.getBusinessId());
            if (vo.getCommand().equals(CommonProperty.DisuseCommand.DISUSE)) {
                user.setDeleteFlag(Boolean.TRUE);
            } else if (vo.getCommand().equals(CommonProperty.DisuseCommand.DISUSE_CANCEL)) {
                user.setDeleteFlag(Boolean.FALSE);
            }
            user.setModifyById(vo.getOperationById());
            user.setModifyByName(vo.getOperationByName());
            user.setModifyByDate(Calendar.getInstance().getTime());
            save(user);

            logger.info("3. " + (vo.getCommand() == 1 ? "作废" : "取消作废") + "用户角色关联");
            List<UserRole> userRoleList = userRoleService.getListByUserId(vo.getBusinessId());
            userRoleList.forEach(userRole -> {
                if (vo.getCommand().equals(CommonProperty.DisuseCommand.DISUSE)) {
                    userRole.setDeleteFlag(Boolean.TRUE);
                } else if (vo.getCommand().equals(CommonProperty.DisuseCommand.DISUSE_CANCEL)) {
                    userRole.setDeleteFlag(Boolean.FALSE);
                }
                userRole.setModifyById(vo.getOperationById());
                userRole.setModifyByName(vo.getOperationByName());
                userRole.setModifyByDate(Calendar.getInstance().getTime());
            });
            userRoleService.save(userRoleList);

            logger.info("4. 添加操作记录");
            operationRecordService.save(UserOperationRecord.newInstance(vo.getBusinessId(), vo.getOperationById(), vo.getOperationByName(), vo.getDescription()));
        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error("作废失败!", e);
            throw e;
        }
        return ResultsUtil.getSuccessResultMap((vo.getCommand() == 1 ? "作废" : "取消作废") + "成功!");
    }

    private void disuseByDataValidation(DisuseUserVo vo) {
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
            throw new TmsDataValidationException("作废命令不能为空!");
        }
        if (!Ints.contains(CommonProperty.DisuseCommand.COMMANDS, vo.getCommand())) {
            throw new TmsDataValidationException("作废命令无效!");
        }
    }

    @Override
    public Map update(UpdateUserVo vo) {
        try {
            logger.info("1. 数据校验");
            updateByDataValidation(vo);

            logger.info("2. 更新用户");
            save(vo.getUser());

            logger.info("3. 添加操作记录");
            operationRecordService.save(UserOperationRecord.newInstance(vo.getBusinessId(), vo.getOperationById(), vo.getOperationByName(), vo.getDescription()));
        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error("更新失败!", e);
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("更新成功!");
    }

    private void updateByDataValidation(UpdateUserVo vo) {
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
        if (Objects.isNull(vo.getUser())) {
            throw new TmsDataValidationException("用户对象不能为空!");
        }
    }

    @Override
    public Page<User> getUserPage(QueryUserVo vo, Integer page, Integer size) {
        return repository.findByAuto(vo.getUser(), new PageRequest(page, size));
    }

    @Override
    public Map getUserViewPage(Page<User> page) {
        List<UserView> content = Lists.newArrayList();
        page.getContent().forEach(user -> {
            String userId = user.getId();
            List<UserRole> userRoleList = userRoleService.getListByUserId(userId);
            List<String> roleIdList = Lists.newArrayList();
            for (UserRole userRole : userRoleList) {
                roleIdList.add(userRole.getRoleId());
            }
            List<Role> roleList = roleService.getListById(roleIdList);
            List<UserOperationRecord> operationRecords = operationRecordService.getListByUserId(userId);
            content.add(UserView.newInstance(user, userRoleList, roleList, operationRecords));
        });
        return ResultsUtil.getSuccessResultMap(PageUtil.newInstancePage(page, content));
    }
}
