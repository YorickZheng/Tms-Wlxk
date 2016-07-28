package com.wlxk.support.vo;

/**
 * 基础操作视图类
 * 定义了公用字段
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/27
 */
public abstract class BasicOperationVo {
    private String businessId;
    private String operationById;
    private String operationByName;
    private String description;

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
