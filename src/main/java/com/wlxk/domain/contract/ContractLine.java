package com.wlxk.domain.contract;

import com.wlxk.domain.BasicDomain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 合同线路类
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/26
 */
@Entity
@Table(name = "tms_contract_line")
public class ContractLine extends BasicDomain {
    /** 合同ID */
    private String contractId;
    /** 网点ID */
    private String branchId;
    /** 转运公司名称 */
    private String convertCompanyName;
    /** 开始站 */
    private String startStation;
    /** 结束站 */
    private String endStation;

    public static ContractLine newInstance(String branchId, String convertCompanyName, String startStation, String endStation) {
        ContractLine line = new ContractLine();
        line.setBranchId(branchId);
        line.setConvertCompanyName(convertCompanyName);
        line.setStartStation(startStation);
        line.setEndStation(endStation);
        return line;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getConvertCompanyName() {
        return convertCompanyName;
    }

    public void setConvertCompanyName(String convertCompanyName) {
        this.convertCompanyName = convertCompanyName;
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
}
