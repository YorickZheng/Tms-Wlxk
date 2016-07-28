package com.wlxk.domain.contract;

import com.wlxk.domain.BasicOperationRecord;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 合同操作记录类
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/26
 */
@Entity
@Table(name = "tms_contract_operation_record")
public class ContractOperationRecord extends BasicOperationRecord {
    public static ContractOperationRecord newInstance(String businessId, String operationById, String operationByName, String description) {
        ContractOperationRecord operationRecord = new ContractOperationRecord();
        operationRecord.setBusinessId(businessId);
        operationRecord.setOperationById(operationById);
        operationRecord.setOperationByName(operationByName);
        operationRecord.setDescription(description);
        return operationRecord;
    }
}
