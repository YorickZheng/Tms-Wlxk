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
    /** 合同ID */
    private String contractId;

    public static ContractOperationRecord newDefaultInstance(String contractId, String description, Boolean status) {
        ContractOperationRecord operationRecord = new ContractOperationRecord();
        operationRecord.setContractId(contractId);
        operationRecord.setDescription(description);
        operationRecord.setStatus(status);
        return operationRecord;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
}
