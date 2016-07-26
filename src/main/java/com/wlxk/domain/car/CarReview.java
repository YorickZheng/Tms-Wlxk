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
    /** 车辆ID */
    private String carId;

    public static CarReview newDefaultInstance(String carId) {
        CarReview review = new CarReview();
        review.setCarId(carId);
        review.setStatus(0);
        review.setSort(0);
        return review;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }
}
