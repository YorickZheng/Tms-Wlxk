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
    private List<String> menuIdList;

    public List<String> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<String> menuIdList) {
        this.menuIdList = menuIdList;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
