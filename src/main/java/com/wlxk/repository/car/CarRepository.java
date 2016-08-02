package com.wlxk.repository.car;

import com.wlxk.domain.car.Car;
import com.wlxk.support.CustomRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 车辆仓储层
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/26
 */
public interface CarRepository extends PagingAndSortingRepository<Car, String>, JpaSpecificationExecutor<Car> {
}
