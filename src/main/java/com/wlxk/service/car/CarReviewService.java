package com.wlxk.service.car;

import com.wlxk.domain.car.CarReview;

import java.util.List;

/**
 * Created by malin on 2016/7/26.
 */
public interface CarReviewService {
    CarReview save(CarReview carReview);
    Iterable<CarReview> save(List<CarReview> list);
    CarReview getOneByCarId(String carId);
    List<CarReview> getListByCarId(String carId);
}
