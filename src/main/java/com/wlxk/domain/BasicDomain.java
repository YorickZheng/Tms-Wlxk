package com.wlxk.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * 基类存放公用字段
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/22
 */
@MappedSuperclass
public class BasicDomain implements Serializable {
    /** 主键编号 */
    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    private String id;
    /** 创建者编号 */
    private String createById = "sysId";
    /** 创建者名称 */
    private String createByName = "系统";
    /** 创建时间 */
    private Date createByDate = Calendar.getInstance().getTime();
    /** 修改者编号 */
    private String modifyById = "sysId";
    /** 修改这名称 */
    private String modifyByName = "系统";
    /** 修改时间 */
    private Date modifyByDate = Calendar.getInstance().getTime();
    /** 删除标识 默认 false */
    private Boolean deleteFlag = Boolean.FALSE;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateById() {
        return createById;
    }

    public void setCreateById(String createById) {
        this.createById = createById;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public Date getCreateByDate() {
        return createByDate;
    }

    public void setCreateByDate(Date createByDate) {
        this.createByDate = createByDate;
    }

    public String getModifyById() {
        return modifyById;
    }

    public void setModifyById(String modifyById) {
        this.modifyById = modifyById;
    }

    public String getModifyByName() {
        return modifyByName;
    }

    public void setModifyByName(String modifyByName) {
        this.modifyByName = modifyByName;
    }

    public Date getModifyByDate() {
        return modifyByDate;
    }

    public void setModifyByDate(Date modifyByDate) {
        this.modifyByDate = modifyByDate;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
