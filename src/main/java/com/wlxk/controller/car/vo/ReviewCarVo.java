package com.wlxk.controller.car.vo;

/**
 * Created by malin on 2016/7/26.
 */
public class ReviewCarVo {
    private String carId;
    private String reviewById;
    private String reviewByName;
    private String description;

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
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
