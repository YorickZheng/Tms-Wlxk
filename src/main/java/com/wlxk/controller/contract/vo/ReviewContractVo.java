package com.wlxk.controller.contract.vo;

/**
 * Created by malin on 2016/7/26.
 */
public class ReviewContractVo {
    private String contractId;
    private String reviewById;
    private String reviewByName;
    private String description;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getReviewById() {
        return reviewById;
    }

    public void setReviewById(String reviewById) {
        this.reviewById = reviewById;
    }

    public String getReviewByName() {
        return reviewByName;
    }

    public void setReviewByName(String reviewByName) {
        this.reviewByName = reviewByName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
