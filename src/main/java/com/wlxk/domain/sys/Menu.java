package com.wlxk.domain.sys;

import com.wlxk.domain.BasicDomain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 菜单类
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/22
 */
@Entity
@Table(name = "sys_menu")
public class Menu extends BasicDomain {
    /** 名称 */
    private String name;
    /** 编码 */
    private String code;
    /** 资源路径 */
    private String url;
    /** 图片路径 */
    private String imageUrl;
    /** 说明 */
    private String description;
    /** 等级 */
    private Integer level = 0;
    /** 父菜单Id */
    private String parentMenuId = "0";

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId;
    }
}
