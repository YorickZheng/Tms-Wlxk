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
    /** 审核人ID */
    private String reviewById;
    /** 审核人名称 */
    private String reviewByPerson;
    /** 说明 */
    private String description;
    /** 审核状态 */
    private Integer status;

    public String getReviewById() {
        return reviewById;
    }

    public void setReviewById(String reviewById) {
        this.reviewById = reviewById;
    }

    public String getReviewByPerson() {
        return reviewByPerson;
    }

    public void setReviewByPerson(String reviewByPerson) {
        this.reviewByPerson = reviewByPerson;
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
