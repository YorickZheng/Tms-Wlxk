package com.wlxk.service.tradebill.impl;

import com.wlxk.domain.tradebill.TradeBillReview;
import com.wlxk.repository.tradebill.TradeBillReviewRepository;
import com.wlxk.service.tradebill.TradeBillReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by malin on 2016/7/22.
 */
@Service
public class TradeBillReviewServiceImpl implements TradeBillReviewService {
    @Autowired(required = false)
    private TradeBillReviewRepository tradeBillReviewRepository;

    @Override
    public TradeBillReview save(TradeBillReview tradeBillReview) {
        return tradeBillReviewRepository.save(tradeBillReview);
    }

    @Override
    public Iterable<TradeBillReview> save(List<TradeBillReview> list) {
        return tradeBillReviewRepository.save(list);
    }

    @Override
    public TradeBillReview getOneTradeBillReview(String tradeBillId) {
        return tradeBillReviewRepository.findOneByTradeBillId(tradeBillId);
    }

    @Override
    public List<TradeBillReview> getListByTradeBillId(String tradeBillId) {
        return tradeBillReviewRepository.findByTradeBillId(tradeBillId);
    }
}
