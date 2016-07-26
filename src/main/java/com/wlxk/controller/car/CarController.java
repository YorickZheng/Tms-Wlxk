package com.wlxk.controller.car;

import com.wlxk.controller.car.vo.AddCarVo;
import com.wlxk.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by malin on 2016/7/26.
 */
@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired(required = false)
    private CarService carService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Map add(@RequestBody AddCarVo vo) {
        try {
            return carService.add(vo);
        } catch (Exception e) {
            throw e;
        }
    }
}
