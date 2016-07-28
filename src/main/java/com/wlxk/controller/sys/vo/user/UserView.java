package com.wlxk.controller.sys.vo.user;

import com.wlxk.domain.sys.Role;
import com.wlxk.domain.sys.User;
import com.wlxk.domain.sys.UserOperationRecord;
import com.wlxk.domain.sys.UserRole;

import java.util.List;

/**
 * Created by malin on 2016/7/28.
 */
public class UserView {
    private User user;
    private List<UserRole> userRoleList;
    private List<Role> roleList;
    private List<UserOperationRecord> operationRecordList;
    
    public static UserView newInstance(User user, List<UserRole> userRoleList, List<Role> roleList, List<UserOperationRecord> operationRecordList) {
        UserView view = new UserView();
        view.setUser(user);
        view.setUserRoleList(userRoleList);
        view.setRoleList(roleList);
        view.setOperationRecordList(operationRecordList);
        return view;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserRole> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<UserRole> userRoleList) {
        this.userRoleList = userRoleList;
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
}
