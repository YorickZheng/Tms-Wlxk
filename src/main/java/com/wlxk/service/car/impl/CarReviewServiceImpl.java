package com.wlxk.service.car.impl;

import com.wlxk.domain.car.CarReview;
import com.wlxk.repository.car.CarReviewRepository;
import com.wlxk.service.car.CarReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by malin on 2016/7/26.
 */
@Service
public class CarReviewServiceImpl implements CarReviewService {

    @Autowired(required = false)
    private CarReviewRepository repository;

    @Override
    public CarReview save(CarReview carReview) {
        return repository.save(carReview);
    }

    @Override
    public Iterable<CarReview> save(List<CarReview> list) {
        return repository.save(list);
    }

    @Override
    public CarReview getOneByCarId(String carId) {
        return repository.findOneByBusinessId(carId);
    }

    @Override
    public List<CarReview> getListByCarId(String carId) {
        return repository.findByBusinessId(carId);
    }
}
