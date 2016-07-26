package com.wlxk.service.tradebill;

import com.wlxk.domain.tradebill.TradeBillReview;

import java.util.List;

/**
 * Created by malin on 2016/7/22.
 */
public interface TradeBillReviewService {
    TradeBillReview save(TradeBillReview tradeBillReview);
    Iterable<TradeBillReview> save(List<TradeBillReview> list);
    TradeBillReview getOneTradeBillReview(String tradeBillId);
    List<TradeBillReview> getListByTradeBillId(String tradeBillId);
    
}
