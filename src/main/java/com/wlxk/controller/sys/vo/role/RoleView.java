package com.wlxk.controller.sys.vo.role;

import com.wlxk.domain.sys.Menu;
import com.wlxk.domain.sys.Role;

import java.util.List;

/**
 * Created by malin on 2016/7/28.
 */
public class RoleView {
    private Role role;
    private List<Menu> menuList;

    public static RoleView newInstance(Role role, List<Menu> menuList) {
        RoleView view = new RoleView();
        view.setRole(role);
        view.setMenuList(menuList);
        return view;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }
}
