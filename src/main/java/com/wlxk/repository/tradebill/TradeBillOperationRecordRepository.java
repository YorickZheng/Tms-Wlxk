package com.wlxk.repository.tradebill;

import com.wlxk.domain.tradebill.TradeBillOperationRecord;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 交易单操作记录仓储层
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/22
 */
public interface TradeBillOperationRecordRepository extends PagingAndSortingRepository<TradeBillOperationRecord, String> {
    List<TradeBillOperationRecord> findByTradeBillId(String tradeBillId);
}
