package com.wlxk.repository.tradebill;

import com.wlxk.domain.tradebill.TradeBill;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 交易单仓储层
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/22
 */
public interface TradeBillRepository extends PagingAndSortingRepository<TradeBill, String>, JpaSpecificationExecutor<TradeBill> {
    TradeBill findByTradeBillNo(String tradeBillNo);
    List<TradeBill> findByIdIn(List<String> idList);
    List<TradeBill> findByContractId(String contractId);
}
