package com.wlxk.service.car;

import com.wlxk.controller.car.vo.AddCarVo;
import com.wlxk.controller.car.vo.DisuseCarVo;
import com.wlxk.controller.car.vo.ReviewCarVo;
import com.wlxk.controller.car.vo.UpdateCarVo;
import com.wlxk.domain.car.Car;

import java.util.Map;

/**
 * Created by malin on 2016/7/26.
 */
public interface CarService {
    Car save(Car car);
    Map add(AddCarVo vo);
    Map review(ReviewCarVo vo);
    Map update(UpdateCarVo vo);
    Map disuse(DisuseCarVo vo);

}
