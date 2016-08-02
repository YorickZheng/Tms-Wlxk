package com.wlxk.domain.contract;

import com.wlxk.domain.BasicReview;
import com.wlxk.domain.tradebill.TradeBillReview;
import com.wlxk.support.util.CommonProperty;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 合同审核类
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/26
 */
@Entity
@Table(name = "tms_contract_review")
public class ContractReview extends BasicReview {
    public static ContractReview newDefaultInstance(String businessId) {
        ContractReview review = new ContractReview();
        review.setBusinessId(businessId);
        review.setStatus(CommonProperty.ReviewStatus.UNAUDITED);
        return review;
    }
}
