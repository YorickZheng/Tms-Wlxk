package com.wlxk.service.car;

import com.wlxk.controller.branch.vo.QueryBranchVo;
import com.wlxk.controller.car.vo.*;
import com.wlxk.domain.car.Car;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;
import java.util.Map;

/**
 * Created by malin on 2016/7/26.
 */
@Transactional
public interface CarService {
    Car save(Car car);

    Map add(AddCarVo vo);

    Map review(ReviewCarVo vo);

    Map update(UpdateCarVo vo);

    Map disuse(DisuseCarVo vo);

    Page pageData(QueryCarVo vo);

    Map pageView(QueryCarVo vo);
}
