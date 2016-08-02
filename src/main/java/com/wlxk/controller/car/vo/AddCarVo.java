package com.wlxk.controller.car.vo;

import com.wlxk.domain.car.Car;
import com.wlxk.domain.car.Driver;
import com.wlxk.support.vo.OperationVo;

import java.util.List;

/**
 * Created by malin on 2016/7/26.
 */
public class AddCarVo extends OperationVo {
    private Car car;
    private List<Driver> driverList;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Driver> getDriverList() {
        return driverList;
    }

    public void setDriverList(List<Driver> driverList) {
        this.driverList = driverList;
    }
}
