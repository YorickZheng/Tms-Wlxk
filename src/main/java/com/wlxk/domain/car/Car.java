package com.wlxk.domain.car;

import com.wlxk.domain.BasicDomain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 车辆类
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/26
 */
@Entity
@Table(name = "tms_car")
public class Car extends BasicDomain {
    /** 车牌号 */
    private String numberPlate;
    /** 挂甩尾号1 */
    private String expandNumberPlate1;
    /** 挂甩尾号2 */
    private String expandNumberPlate2;
    /** 挂甩尾号3 */
    private String expandNumberPlate3;
    /** 跟车手机 */
    private String carPhone;
    /** 期望流向开始城市 */
    private String expectStart;
    /** 期望流向结束城市 */
    private String expectEnd;


    // 汽车信息
    /** 宽度 */
    private Float width;
    /** 高度 */
    private Float height;
    /** 载重 */
    private Float loads;
    /** 容积 */
    private Float volume;
    /** 车辆类型 */
    private Integer carType;
    /** 发送机号 */
    private String engineNo;
    /** 车辆照片路径 */
    private String carImageUrl;
    /** 行驶证照片路径 */
    private String drivingLicense;

    // 联系人
    /** 联系人 */
    private String linkman;
    /** 联系人手机 */
    private String linkmanPhone;
    /** 联系人地址 */
    private String linkmanAddress;

    // 车辆状态
    /** 认证状态 */
    private Integer reviewStatus;
    /** 运输状态 */
    private Integer runStatus;

    /** 网点ID */
    private String branchId;
    /** 网点NO */
    private String branchNo;
    /** 网点名称 */
    private String branchName;

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getExpandNumberPlate1() {
        return expandNumberPlate1;
    }

    public void setExpandNumberPlate1(String expandNumberPlate1) {
        this.expandNumberPlate1 = expandNumberPlate1;
    }

    public String getExpandNumberPlate2() {
        return expandNumberPlate2;
    }

    public void setExpandNumberPlate2(String expandNumberPlate2) {
        this.expandNumberPlate2 = expandNumberPlate2;
    }

    public String getExpandNumberPlate3() {
        return expandNumberPlate3;
    }

    public void setExpandNumberPlate3(String expandNumberPlate3) {
        this.expandNumberPlate3 = expandNumberPlate3;
    }

    public String getCarPhone() {
        return carPhone;
    }

    public void setCarPhone(String carPhone) {
        this.carPhone = carPhone;
    }

    public String getExpectStart() {
        return expectStart;
    }

    public void setExpectStart(String expectStart) {
        this.expectStart = expectStart;
    }

    public String getExpectEnd() {
        return expectEnd;
    }

    public void setExpectEnd(String expectEnd) {
        this.expectEnd = expectEnd;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getLoads() {
        return loads;
    }

    public void setLoads(Float loads) {
        this.loads = loads;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public Integer getCarType() {
        return carType;
    }

    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getCarImageUrl() {
        return carImageUrl;
    }

    public void setCarImageUrl(String carImageUrl) {
        this.carImageUrl = carImageUrl;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getLinkmanPhone() {
        return linkmanPhone;
    }

    public void setLinkmanPhone(String linkmanPhone) {
        this.linkmanPhone = linkmanPhone;
    }

    public String getLinkmanAddress() {
        return linkmanAddress;
    }

    public void setLinkmanAddress(String linkmanAddress) {
        this.linkmanAddress = linkmanAddress;
    }

    public Integer getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(Integer reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public Integer getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(Integer runStatus) {
        this.runStatus = runStatus;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
