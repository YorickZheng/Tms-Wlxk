package com.wlxk.service.tradebill.impl;

import com.wlxk.domain.tradebill.TradeBillOperationRecord;
import com.wlxk.repository.tradebill.TradeBillOperationRecordRepository;
import com.wlxk.service.tradebill.TradeBillOperationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by malin on 2016/7/22.
 */
@Service
public class TradeBillOperationRecordServiceImpl implements TradeBillOperationRecordService {

    @Autowired
    private TradeBillOperationRecordRepository tradeBillOperationRecordRepository;

    @Override
    public TradeBillOperationRecord save(TradeBillOperationRecord operationRecord) {
        return tradeBillOperationRecordRepository.save(operationRecord);
    }

    @Override
    public List<TradeBillOperationRecord> getListByTradeBillid(String tradeBillId) {
        return tradeBillOperationRecordRepository.findByTradeBillId(tradeBillId);
    }
}
