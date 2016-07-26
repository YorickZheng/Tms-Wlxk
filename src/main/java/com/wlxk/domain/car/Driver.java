package com.wlxk.domain.car;

import com.wlxk.domain.BasicDomain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 司机类
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/26
 */
@Entity
@Table(name = "tms_driver")
public class Driver extends BasicDomain {
    /** 车辆ID */
    private String carId;
    /** 联系人 */
    private String linkman;
    /** 手机 */
    private String phone;
    /** 驾驶证号码 */
    private String driversLicenseNo;
    /** 详细地址 */
    private String address;

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDriversLicenseNo() {
        return driversLicenseNo;
    }

    public void setDriversLicenseNo(String driversLicenseNo) {
        this.driversLicenseNo = driversLicenseNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
