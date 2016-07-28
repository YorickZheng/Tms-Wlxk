package com.wlxk.service.sys;

import com.wlxk.domain.sys.UserRole;

import java.util.List;

/**
 * Created by malin on 2016/7/27.
 */
public interface UserRoleService {
    UserRole save(UserRole userRole);
    Iterable<UserRole> save(List<UserRole> list);
    List<UserRole> getListByUserId(String userId);
}
