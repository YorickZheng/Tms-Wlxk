package com.wlxk.domain;

import javax.persistence.MappedSuperclass;

/**
 * 操作记录基类
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/22
 */
@MappedSuperclass
public class BasicOperationRecord extends BasicDomain {
    /** 操作人ID */
    private String operationById;
    /** 操作人名称 */
    private String operationByName;
    /** 说明 */
    private String description;
    /** 状态 */
    private Boolean status = Boolean.TRUE;

    public String getOperationById() {
        return operationById;
    }

    public void setOperationById(String operationById) {
        this.operationById = operationById;
    }

    public String getOperationByName() {
        return operationByName;
    }

    public void setOperationByName(String operationByName) {
        this.operationByName = operationByName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
