package com.wlxk.domain.sys;

import com.wlxk.domain.BasicOperationRecord;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by malin on 2016/7/28.
 */
@Entity
@Table(name = "sys_role_operation_record")
public class RoleOperationRecord extends BasicOperationRecord {
    public static RoleOperationRecord newInstance(String businessId, String operationById, String operationByName, String description) {
        RoleOperationRecord operationRecord = new RoleOperationRecord();
        operationRecord.setBusinessId(businessId);
        operationRecord.setOperationById(operationById);
        operationRecord.setOperationByName(operationByName);
        operationRecord.setDescription(description);
        return operationRecord;
    }
}
