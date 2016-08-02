package com.wlxk.domain.tradebill;

import com.wlxk.domain.BasicReview;
import com.wlxk.support.util.CommonProperty;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 交易单审核类
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/22
 */
@Entity
@Table(name = "tms_trade_bill_review")
public class TradeBillReview extends BasicReview {

    public static TradeBillReview newDefaultInstance(String businessId) {
        TradeBillReview review = new TradeBillReview();
        review.setBusinessId(businessId);
        review.setStatus(CommonProperty.ReviewStatus.UNAUDITED);
        return review;
    }

}

