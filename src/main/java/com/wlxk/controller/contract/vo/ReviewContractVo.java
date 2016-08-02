package com.wlxk.controller.contract.vo;

import com.wlxk.support.vo.OperationDataVo;

/**
 * Created by malin on 2016/7/26.
 */
public class ReviewContractVo extends OperationDataVo {
    private Integer command;

    public Integer getCommand() {
        return command;
    }

    public void setCommand(Integer command) {
        this.command = command;
    }
}
