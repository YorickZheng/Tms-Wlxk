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
    /** 网点ID */
    private String branchId;

    public static BranchOperationRecord newDefaultInstance(String branchId, String description, Boolean status) {
        BranchOperationRecord operationRecord = new BranchOperationRecord();
        operationRecord.setBranchId(branchId);
        operationRecord.setDescription(description);
        operationRecord.setStatus(status);
        return operationRecord;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
}
