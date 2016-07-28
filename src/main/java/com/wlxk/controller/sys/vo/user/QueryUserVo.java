package com.wlxk.controller.sys.vo.user;

import com.wlxk.domain.sys.User;
import com.wlxk.domain.sys.UserRole;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by malin on 2016/7/28.
 */
public class QueryUserVo {
    private User user;
    private List<String> roleListId;
    private Integer page;
    private Integer size;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getRoleListId() {
        return roleListId;
    }

    public void setRoleListId(List<String> roleListId) {
        this.roleListId = roleListId;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
