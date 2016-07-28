package com.wlxk.domain.branch;

import com.wlxk.domain.BasicOperationRecord;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 网点操作记录类
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/26
 */
@Entity
@Table(name = "tms_branch_operation_record")
public class BranchOperationRecord extends BasicOperationRecord {
    public static BranchOperationRecord newInstance(String businessId, String operationById, String operationByName, String description) {
        BranchOperationRecord operationRecord = new BranchOperationRecord();
        operationRecord.setBusinessId(businessId);
        operationRecord.setOperationById(operationById);
        operationRecord.setOperationByName(operationByName);
        operationRecord.setDescription(description);
        return operationRecord;
    }
}
