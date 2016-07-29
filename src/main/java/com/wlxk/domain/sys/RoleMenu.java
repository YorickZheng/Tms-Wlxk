package com.wlxk.domain.sys;

import com.wlxk.domain.BasicDomain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 角色菜单类
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/22
 */
@Entity
@Table(name = "sys_role_menu")
public class RoleMenu extends BasicDomain {
    /** 角色编号 */
    private String roleId;
    /** 菜单编号 */
    private String menuId;

    public static RoleMenu getInstance(String roleId, String menuId) {
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setRoleId(roleId);
        roleMenu.setMenuId(menuId);
        return roleMenu;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
