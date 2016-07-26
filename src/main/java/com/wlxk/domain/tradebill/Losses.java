package com.wlxk.domain.tradebill;

import com.wlxk.domain.BasicDomain;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 费用类
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/22
 */
@Entity
@Table(name = "tms_losses")
public class Losses extends BasicDomain {
    /** 交易单ID */
    private String tradeBillId;
    /** 交易单NO */
    private String tradeBillNo;
    /** 费用类型 */
    private Integer feeType;
    /** 挂账金额 */
    private BigDecimal money;
    /** 已收金额 默认为0 */
    private BigDecimal incomeMoney = BigDecimal.ZERO;

    public String getTradeBillId() {
        return tradeBillId;
    }

    public void setTradeBillId(String tradeBillId) {
        this.tradeBillId = tradeBillId;
    }

    public String getTradeBillNo() {
        return tradeBillNo;
    }

    public void setTradeBillNo(String tradeBillNo) {
        this.tradeBillNo = tradeBillNo;
    }

    public Integer getFeeType() {
        return feeType;
    }

    public void setFeeType(Integer feeType) {
        this.feeType = feeType;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getIncomeMoney() {
        return incomeMoney;
    }

    public void setIncomeMoney(BigDecimal incomeMoney) {
        this.incomeMoney = incomeMoney;
    }
}
