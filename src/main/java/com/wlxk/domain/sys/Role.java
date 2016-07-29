package com.wlxk.domain.sys;

import com.wlxk.domain.BasicDomain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 角色类
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/22
 */
@Entity
@Table(name = "sys_role")
public class Role extends BasicDomain {
    /** 名称 */
    private String name;
    /** 编码 */
    private String code;
    /** 说明 */
    private String description;

    public static Role getInstance(String name, String code, String description) {
        Role role = new Role();
        role.setName(name);
        role.setCode(code);
        role.setDescription(description);
        return role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
