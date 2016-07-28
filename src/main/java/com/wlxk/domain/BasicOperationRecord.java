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
    /**
     * 业务ID
     */
    private String businessId;
    /**
     * 操作人ID
     */
    private String operationById;
    /**
     * 操作人名称
     */
    private String operationByName;
    /**
     * 说明
     */
    private String description;

    public static BasicOperationRecord newInstance(String businessId, String operationById, String operationByName, String description) {
        BasicOperationRecord operationRecord = new BasicOperationRecord();
        operationRecord.setBusinessId(businessId);
        operationRecord.setOperationById(operationById);
        operationRecord.setOperationByName(operationByName);
        operationRecord.setDescription(description);
        return operationRecord;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

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

}
