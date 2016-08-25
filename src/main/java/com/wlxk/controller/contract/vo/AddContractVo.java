package com.wlxk.controller.contract.vo;

import com.wlxk.domain.car.Driver;
import com.wlxk.domain.contract.Contract;
import com.wlxk.domain.contract.ContractLine;
import com.wlxk.support.vo.OperationVo;

import java.util.List;

/**
 * Created by malin on 2016/7/26.
 */
public class AddContractVo extends OperationVo {
    private Contract contract;
    private List<ContractLine> lineList;

    public static AddContractVo newInstance(Contract contract, List<ContractLine> lineList) {
        AddContractVo vo = new AddContractVo();
        vo.setContract(contract);
        vo.setLineList(lineList);
        return vo;
    }

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
