package com.wlxk.controller.sys.vo.user;

import com.wlxk.domain.sys.User;
import com.wlxk.support.vo.BasicOperationVo;

/**
 * Created by malin on 2016/7/28.
 */
public class UpdateUserVo extends BasicOperationVo {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
