package com.wlxk.controller.sys.vo.user;

import com.wlxk.domain.sys.*;

import java.util.List;

/**
 * Created by malin on 2016/7/28.
 */
public class UserView {
    private User user;
    private List<Role> roleList;
    private List<Menu> menuList;
    private List<UserOperationRecord> operationRecordList;
    
    public static UserView newInstance(User user,
                                       List<Role> roleList,
                                       List<UserOperationRecord> operationRecordList) {
        UserView view = new UserView();
        view.setUser(user);
        view.setRoleList(roleList);
        view.setOperationRecordList(operationRecordList);
        return view;
    }

    public static UserView newInstance(User user,
                                       List<Role> roleList,
                                       List<Menu> menuList,
                                       List<UserOperationRecord> operationRecordList) {
        UserView view = new UserView();
        view.setUser(user);
        view.setRoleList(roleList);
        view.setMenuList(menuList);
        view.setOperationRecordList(operationRecordList);
        return view;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<UserOperationRecord> getOperationRecordList() {
        return operationRecordList;
    }

    public void setOperationRecordList(List<UserOperationRecord> operationRecordList) {
        this.operationRecordList = operationRecordList;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }
}
