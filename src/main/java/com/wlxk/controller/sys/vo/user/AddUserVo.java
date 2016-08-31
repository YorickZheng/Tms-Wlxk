package com.wlxk.controller.sys.vo.user;

import com.wlxk.domain.sys.User;
import com.wlxk.domain.sys.UserRole;
import com.wlxk.support.vo.OperationVo;

import java.util.List;

/**
 * Created by malin on 2016/7/27.
 */
public class AddUserVo extends OperationVo {
    private User user;
    private List<String> roleIdList;

    public List<String> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<String> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
