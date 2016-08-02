package com.wlxk.domain.car;

import com.wlxk.domain.BasicReview;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 车辆审核类
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/26
 */
@Entity
@Table(name = "tms_car_review")
public class CarReview extends BasicReview {

    public static CarReview newDefaultInstance(String businessId) {
        CarReview review = new CarReview();
        review.setBusinessId(businessId);
        review.setStatus(0);
        return review;
    }
}
