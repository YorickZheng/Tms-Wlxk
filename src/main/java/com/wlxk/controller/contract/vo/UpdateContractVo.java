package com.wlxk.controller.contract.vo;

import com.wlxk.domain.contract.Contract;
import com.wlxk.domain.contract.ContractLine;

import java.util.List;

/**
 * Created by malin on 2016/7/26.
 */
public class UpdateContractVo {
    private Contract contract;
    private List<ContractLine> lineList;

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public List<ContractLine> getLineList() {
        return lineList;
    }

    public void setLineList(List<ContractLine> lineList) {
        this.lineList = lineList;
    }
}
