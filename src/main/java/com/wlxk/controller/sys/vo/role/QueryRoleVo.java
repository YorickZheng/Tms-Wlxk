package com.wlxk.controller.sys.vo.role;

import com.wlxk.domain.sys.Role;

/**
 * Created by malin on 2016/7/28.
 */
public class QueryRoleVo {
    private Role role;
    private int page;
    private int size;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
