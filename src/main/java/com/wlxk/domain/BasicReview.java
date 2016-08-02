package com.wlxk.domain;

import javax.persistence.MappedSuperclass;

/**
 * 审核记录基类
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/22
 */
@MappedSuperclass
public class BasicReview extends BasicDomain {
    /**
     * 业务ID
     */
    private String businessId;
    /**
     * 审核人ID
     */
    private String reviewById;
    /**
     * 审核人名称
     */
    private String reviewByName;
    /**
     * 说明
     */
    private String description;
    /**
     * 审核状态
     */
    private Integer status;

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
