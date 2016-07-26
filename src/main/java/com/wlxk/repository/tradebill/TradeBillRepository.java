package com.wlxk.repository.tradebill;

import com.wlxk.domain.tradebill.TradeBill;
import com.wlxk.support.CustomRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 交易单仓储层
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/22
 */
public interface TradeBillRepository extends CustomRepository<TradeBill, String> {
    TradeBill findOneByTradeBillNo(String tradeBillNo);
}
