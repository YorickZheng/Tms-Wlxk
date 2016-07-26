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
    /** 交易单ID */
    private String tradeBillId;

    public static TradeBillOperationRecord newDefaultInstance(String tradeBillId, String description, Boolean status) {
        TradeBillOperationRecord operationRecord = new TradeBillOperationRecord();
        operationRecord.setTradeBillId(tradeBillId);
        operationRecord.setDescription(description);
        operationRecord.setStatus(status);
        return operationRecord;
    }

    public String getTradeBillId() {
        return tradeBillId;
    }

    public void setTradeBillId(String tradeBillId) {
        this.tradeBillId = tradeBillId;
    }
}
