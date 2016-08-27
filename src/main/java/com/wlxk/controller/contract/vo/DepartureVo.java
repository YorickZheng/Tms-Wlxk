package com.wlxk.controller.contract.vo;

import com.wlxk.support.vo.OperationVo;

/**
 * Created by 马林 on 2016/8/26.
 */
public class DepartureVo extends OperationVo {
    private String contractId;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
}
