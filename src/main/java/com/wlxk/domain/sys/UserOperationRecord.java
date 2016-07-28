package com.wlxk.domain.sys;

import com.wlxk.domain.BasicOperationRecord;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by malin on 2016/7/27.
 */
@Entity
@Table(name = "sys_user_operation_record")
public class UserOperationRecord extends BasicOperationRecord {
    public static UserOperationRecord newInstance(String businessId, String operationById, String operationByName, String description) {
        UserOperationRecord operationRecord = new UserOperationRecord();
        operationRecord.setBusinessId(businessId);
        operationRecord.setOperationById(operationById);
        operationRecord.setOperationByName(operationByName);
        operationRecord.setDescription(description);
        return operationRecord;
    }
}
