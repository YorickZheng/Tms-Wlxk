package com.wlxk.controller.car.vo;

import com.wlxk.domain.car.Car;
import com.wlxk.domain.car.Driver;

import java.util.List;

/**
 * Created by malin on 2016/7/26.
 */
public class CarView {
    private Car car;
    private List<Driver> driverList;

    public static CarView newInstance(Car car, List<Driver> driverList) {
        CarView view = new CarView();
        view.setCar(car);
        view.setDriverList(driverList);
        return view;
    }

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
