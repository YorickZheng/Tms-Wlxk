package com.wlxk.service.tradebill;

import com.wlxk.domain.tradebill.TradeBillOperationRecord;

import java.util.List;

/**
 * Created by malin on 2016/7/22.
 */
public interface TradeBillOperationRecordService {
    TradeBillOperationRecord save(TradeBillOperationRecord operationRecord);
    List<TradeBillOperationRecord> getListByTradeBillId(String tradeBillId);
}
