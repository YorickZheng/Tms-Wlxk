package com.wlxk.domain.tradebill;

import com.wlxk.domain.BasicReview;

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
    /** 交易单ID */
    private String tradeBillId;

    public static TradeBillReview newDefaultInstance(String tradeBillId) {
        TradeBillReview review = new TradeBillReview();
        review.setTradeBillId(tradeBillId);
        review.setStatus(0);
        review.setSort(0);
        return review;
    }

    public String getTradeBillId() {
        return tradeBillId;
    }

    public void setTradeBillId(String tradeBillId) {
        this.tradeBillId = tradeBillId;
    }

}

