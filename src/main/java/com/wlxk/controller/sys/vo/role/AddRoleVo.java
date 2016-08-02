package com.wlxk.controller.sys.vo.role;

import com.wlxk.domain.sys.Role;
import com.wlxk.domain.sys.RoleMenu;
import com.wlxk.support.vo.OperationVo;

import java.util.List;

/**
 * Created by malin on 2016/7/28.
 */
public class AddRoleVo extends OperationVo {
    private Role role;
    private List<RoleMenu> roleMenuList;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<RoleMenu> getRoleMenuList() {
        return roleMenuList;
    }

    public void setRoleMenuList(List<RoleMenu> roleMenuList) {
        this.roleMenuList = roleMenuList;
    }
}
