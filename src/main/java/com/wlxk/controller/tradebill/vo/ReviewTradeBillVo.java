package com.wlxk.controller.tradebill.vo;

import com.wlxk.support.vo.OperationDataVo;

/**
 * 交易单审核 Vo
 * Created by malin on 2016/7/24.
 */
public class ReviewTradeBillVo extends OperationDataVo {
    private Integer command;

    public Integer getCommand() {
        return command;
    }

    public void setCommand(Integer command) {
        this.command = command;
    }
}
