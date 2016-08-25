package com.wlxk.controller.contract.vo;


import com.sun.tools.javac.util.List;
import com.wlxk.support.vo.OperationVo;

/**
 * Created by 马林 on 2016/8/25.
 */
public class LoadingVo extends OperationVo {
    private String contractId;
    private List<String> tradeBillIdList;

    public static LoadingVo newInstance(String contractId, List<String> tradeBillIdList) {
        LoadingVo vo = new LoadingVo();
        vo.setContractId(contractId);
        vo.setTradeBillIdList(tradeBillIdList);
        return vo;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public List<String> getTradeBillIdList() {
        return tradeBillIdList;
    }

    public void setTradeBillIdList(List<String> tradeBillIdList) {
        this.tradeBillIdList = tradeBillIdList;
    }
}
