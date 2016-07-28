package com.wlxk.domain.tradebill;

import com.wlxk.domain.BasicOperationRecord;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 交易单操作记录类
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/22
 */
@Entity
@Table(name = "tms_trade_bill_operation_record")
public class TradeBillOperationRecord extends BasicOperationRecord {
    public static TradeBillOperationRecord newInstance(String businessId, String operationById, String operationByName, String description) {
        TradeBillOperationRecord operationRecord = new TradeBillOperationRecord();
        operationRecord.setBusinessId(businessId);
        operationRecord.setOperationById(operationById);
        operationRecord.setOperationByName(operationByName);
        operationRecord.setDescription(description);
        return operationRecord;
    }
}
