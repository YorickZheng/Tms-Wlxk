package com.wlxk.repository.tradebill;

import com.wlxk.domain.tradebill.TradeBillReview;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 交易单审核记录仓储层
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/22
 */
public interface TradeBillReviewRepository extends PagingAndSortingRepository<TradeBillReview, String> {
    TradeBillReview findOneByBusinessId(String businessId);
    List<TradeBillReview> findByBusinessId(String businessId);
}
