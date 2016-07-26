package com.wlxk.domain.tradebill;

import com.wlxk.domain.BasicDomain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 交易单类
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/22
 */
@Entity
@Table(name = "tms_trade_bill")
public class TradeBill extends BasicDomain {

    // 发货人信息
    /** 发货人电话 */
    private String sellerTelephone;
    /** 发货客户 */
    private String sellerClient;
    /** 发货人 */
    private String sellerPerson;
    /** 发货人手机 */
    private String sellerPhone;
    /** 发货人地址 */
    private String sellerAddress;

    // 收货人信息
    /** 收货人电话 */
    private String buyerTelephone;
    /** 收货客户 */
    private String buyerClient;
    /** 收货人 */
    private String buyerPerson;
    /** 收货人手机 */
    private String buyerPhone;
    /** 收货人地址 */
    private String buyerAddress;

    // 交易单信息
    /** 交易单NO */
    private String tradeBillNo;
    /** 运输时间 */
    private String transportDate;
    /** 开始站 */
    private String startStation;
    /** 结束站 */
    private String endStation;
    /** 结算方式 */
    private Integer settleType;
    /** 说明 */
    private String description;
    /** 交易单状态 */
    private Integer status;
    /** 网点ID */
    private String branchId;
    /** 网点NO */
    private String branchNo;
    /** 网点名称 */
    private String branchName;
    /** 合同ID */
    private String contractId;
    /** 合同NO */
    private String contractNo;

    public String getSellerTelephone() {
        return sellerTelephone;
    }

    public void setSellerTelephone(String sellerTelephone) {
        this.sellerTelephone = sellerTelephone;
    }

    public String getSellerClient() {
        return sellerClient;
    }

    public void setSellerClient(String sellerClient) {
        this.sellerClient = sellerClient;
    }

    public String getSellerPerson() {
        return sellerPerson;
    }

    public void setSellerPerson(String sellerPerson) {
        this.sellerPerson = sellerPerson;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getBuyerTelephone() {
        return buyerTelephone;
    }

    public void setBuyerTelephone(String buyerTelephone) {
        this.buyerTelephone = buyerTelephone;
    }

    public String getBuyerClient() {
        return buyerClient;
    }

    public void setBuyerClient(String buyerClient) {
        this.buyerClient = buyerClient;
    }

    public String getBuyerPerson() {
        return buyerPerson;
    }

    public void setBuyerPerson(String buyerPerson) {
        this.buyerPerson = buyerPerson;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getTradeBillNo() {
        return tradeBillNo;
    }

    public void setTradeBillNo(String tradeBillNo) {
        this.tradeBillNo = tradeBillNo;
    }

    public String getTransportDate() {
        return transportDate;
    }

    public void setTransportDate(String transportDate) {
        this.transportDate = transportDate;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public Integer getSettleType() {
        return settleType;
    }

    public void setSettleType(Integer settleType) {
        this.settleType = settleType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }
}
