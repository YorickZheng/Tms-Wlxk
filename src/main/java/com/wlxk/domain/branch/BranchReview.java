package com.wlxk.domain.branch;

import com.wlxk.domain.BasicReview;
import com.wlxk.support.util.CommonProperty;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 菜单审核类
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/26
 */
@Entity
@Table(name = "tms_branch_review")
public class BranchReview extends BasicReview {

    public static BranchReview newDefaultInstance(String businessId) {
        BranchReview review = new BranchReview();
        review.setBusinessId(businessId);
        review.setStatus(CommonProperty.ReviewStatus.UNAUDITED);
        return review;
    }
}
