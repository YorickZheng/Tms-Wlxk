package com.wlxk.service.sys;

import com.wlxk.domain.sys.RoleMenu;

import java.util.List;

/**
 * Created by malin on 2016/7/28.
 */
public interface RoleMenuService {
    RoleMenu save(RoleMenu roleMenu);
    Iterable<RoleMenu> save(List<RoleMenu> list);
    List<RoleMenu> getListByRoleId(String roleId);
}
