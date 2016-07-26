package com.wlxk.domain.branch;

import com.wlxk.domain.BasicDomain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 网点银行卡类
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/26
 */
@Entity
@Table(name = "tms_branch_bank_card")
public class BranchBankCard extends BasicDomain {
    /** 网点ID */
    private String branchId;
    /** 银行卡号 */
    private String bankNo;
    /** 开户户名 */
    private String depositName;
    /** 开户银行 */
    private String depositBank;
    /** 开户支行 */
    private String depositChildBank;
    /** 所属城市 */
    private String city;
    /** 身份证号 */
    private String idNumber;

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getDepositName() {
        return depositName;
    }

    public void setDepositName(String depositName) {
        this.depositName = depositName;
    }

    public String getDepositBank() {
        return depositBank;
    }

    public void setDepositBank(String depositBank) {
        this.depositBank = depositBank;
    }

    public String getDepositChildBank() {
        return depositChildBank;
    }

    public void setDepositChildBank(String depositChildBank) {
        this.depositChildBank = depositChildBank;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
}
