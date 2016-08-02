package com.wlxk.repository.car;

import com.wlxk.domain.car.CarReview;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 车辆审核仓储层
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/26
 */
public interface CarReviewRepository extends PagingAndSortingRepository<CarReview, String> {
    List<CarReview> findByBusinessId(String businessId);
    CarReview findOneByBusinessId(String businessId);
}
