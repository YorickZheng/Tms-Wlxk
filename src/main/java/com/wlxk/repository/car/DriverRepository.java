package com.wlxk.repository.car;

import com.wlxk.domain.car.Driver;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 司机仓储层
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/26
 */
public interface DriverRepository extends PagingAndSortingRepository<Driver, String> {
    List<Driver> findByCarId(String carId);
    void deleteByCarId(String carId);
}
