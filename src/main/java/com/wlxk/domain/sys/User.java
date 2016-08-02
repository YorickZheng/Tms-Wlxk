package com.wlxk.domain.sys;

import com.wlxk.domain.BasicDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户类
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/22
 */
@Entity
@Table(name = "sys_user")
public class User extends BasicDomain {
    /** 用户名 */
    @Column(name = "username", unique = true, length = 50)
    private String username;
    /** 密码 */
    private String password;
    /** 名称 */
    private String name;
    /** 组织ID */
    private String branchId;

    public User() {
    }

    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
}
