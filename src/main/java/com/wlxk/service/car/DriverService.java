package com.wlxk.service.car;

import com.wlxk.domain.car.Driver;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by malin on 2016/7/26.
 */
public interface DriverService {
    Driver save(Driver driver);
    Iterable<Driver> save(List<Driver> list);
    List<Driver> getListByCarId(String carId);
    void deleteByCarId(String carId);
}
