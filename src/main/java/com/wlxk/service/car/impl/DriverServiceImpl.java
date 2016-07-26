package com.wlxk.service.car.impl;

import com.wlxk.domain.car.Driver;
import com.wlxk.repository.car.DriverRepository;
import com.wlxk.service.car.DriverService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by malin on 2016/7/26.
 */
public class DriverServiceImpl implements DriverService {

    @Autowired(required = false)
    private DriverRepository repository;

    @Override
    public Driver save(Driver driver) {
        return repository.save(driver);
    }

    @Override
    public Iterable<Driver> save(List<Driver> list) {
        return repository.save(list);
    }

    @Override
    public List<Driver> getListByCarId(String carId) {
        return repository.findByCarId(carId);
    }
}
