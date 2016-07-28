package com.wlxk.domain.branch;

import com.wlxk.domain.BasicReview;

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
    /** 网点ID */
    private String branchId;

    public static BranchReview newDefaultInstance(String branchId) {
        BranchReview review = new BranchReview();
        review.setBranchId(branchId);
        review.setStatus(0);
        return review;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
}
