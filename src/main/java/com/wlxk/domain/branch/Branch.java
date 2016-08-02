package com.wlxk.domain.branch;

import com.wlxk.domain.BasicDomain;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 网点类
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/26
 */
@Entity
@Table(name = "tms_branch")
public class Branch extends BasicDomain {
    /**
     * 父网点ID
     */
    private String parentBranchId = "0";
    /**
     * 等级
     */
    private Integer level = 0;
    /**
     * 网点NO
     */
    private String branchNo;
    /**
     * 网点名称
     */
    private String branchName;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 业务类型
     */
    private Integer businessType;
    /**
     * 所在城市
     */
    private String city;
    /**
     * 发货城市
     */
    private String startCity;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 电话
     */
    private String telephone;
    /**
     * 传真
     */
    private String fax;
    /**
     * 年营业额/万
     */
    private BigDecimal yearTurnover;
    /**
     * 营业执照照片路径
     */
    private String businessLicenseImageUrl;
    /**
     * 网点图片路径
     */
    private String branchImageUrl;

    public String getParentBranchId() {
        return parentBranchId;
    }

    public void setParentBranchId(String parentBranchId) {
        this.parentBranchId = parentBranchId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public BigDecimal getYearTurnover() {
        return yearTurnover;
    }

    public void setYearTurnover(BigDecimal yearTurnover) {
        this.yearTurnover = yearTurnover;
    }

    public String getBusinessLicenseImageUrl() {
        return businessLicenseImageUrl;
    }

    public void setBusinessLicenseImageUrl(String businessLicenseImageUrl) {
        this.businessLicenseImageUrl = businessLicenseImageUrl;
    }

    public String getBranchImageUrl() {
        return branchImageUrl;
    }

    public void setBranchImageUrl(String branchImageUrl) {
        this.branchImageUrl = branchImageUrl;
    }
}
