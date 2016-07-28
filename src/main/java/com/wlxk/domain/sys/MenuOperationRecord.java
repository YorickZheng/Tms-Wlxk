package com.wlxk.domain.sys;

import com.wlxk.domain.BasicOperationRecord;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by malin on 2016/7/28.
 */
@Entity
@Table(name = "sys_menu_operation_record")
public class MenuOperationRecord extends BasicOperationRecord {
    public static MenuOperationRecord newInstance(String businessId, String operationById, String operationByName, String description) {
        MenuOperationRecord operationRecord = new MenuOperationRecord();
        operationRecord.setBusinessId(businessId);
        operationRecord.setOperationById(operationById);
        operationRecord.setOperationByName(operationByName);
        operationRecord.setDescription(description);
        return operationRecord;
    }
}
