package com.wlxk.domain.contract;

import com.wlxk.domain.BasicDomain;
import com.wlxk.support.util.CommonProperty;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 装车合同类
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/26
 */
@Entity
@Table(name = "tms_contract")
public class Contract extends BasicDomain {
    /** 合同NO */
    private String contractNo;
    /** 结算方式 */
    private Integer settleType;
    /** 合同状态 */
    private Integer status;
    /** 合同说明 */
    private String description;

    public static Contract newInstance(String contractNo, String description) {
        Contract contract = new Contract();
        contract.setContractNo(contractNo);
        contract.setSettleType(CommonProperty.SettleType.现金);
        contract.setStatus(CommonProperty.ContractStatus.待装车);
        contract.setDescription(description);
        return contract;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public Integer getSettleType() {
        return settleType;
    }

    public void setSettleType(Integer settleType) {
        this.settleType = settleType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
