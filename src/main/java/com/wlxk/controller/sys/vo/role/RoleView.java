package com.wlxk.controller.sys.vo.role;

import com.wlxk.domain.sys.Menu;
import com.wlxk.domain.sys.Role;
import com.wlxk.domain.sys.RoleMenu;
import com.wlxk.domain.sys.RoleOperationRecord;

import java.util.List;

/**
 * Created by malin on 2016/7/28.
 */
public class RoleView {
    private Role role;
    private List<RoleMenu> roleMenuList;
    private List<Menu> menuList;
    private List<RoleOperationRecord> operationRecordList;

    public static RoleView newInstance(Role role, List<RoleMenu> roleMenuList, List<Menu> menuList, List<RoleOperationRecord> operationRecordList) {
        RoleView view = new RoleView();
        view.setRole(role);
        view.setRoleMenuList(roleMenuList);
        view.setMenuList(menuList);
        view.setOperationRecordList(operationRecordList);
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

    public List<RoleMenu> getRoleMenuList() {
        return roleMenuList;
    }

    public void setRoleMenuList(List<RoleMenu> roleMenuList) {
        this.roleMenuList = roleMenuList;
    }

    public List<RoleOperationRecord> getOperationRecordList() {
        return operationRecordList;
    }

    public void setOperationRecordList(List<RoleOperationRecord> operationRecordList) {
        this.operationRecordList = operationRecordList;
    }
}
