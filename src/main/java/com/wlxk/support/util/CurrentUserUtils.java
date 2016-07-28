package com.wlxk.support.util;

import com.wlxk.domain.sys.User;

/**
 * Created by malin on 2016/7/27.
 */
public class CurrentUserUtils {
    public static User getCurrentUser() {
        User user = new User();
        user.setId("1234567");
        user.setName("管理员");
        return user;
    }
}
