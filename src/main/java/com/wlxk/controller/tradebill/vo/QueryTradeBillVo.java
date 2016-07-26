package com.wlxk.controller.tradebill.vo;

import com.wlxk.domain.tradebill.TradeBill;

/**
 * Created by malin on 2016/7/25.
 */
public class QueryTradeBillVo {
    private TradeBill tradeBill;
    private Integer page;
    private Integer size;

    public TradeBill getTradeBill() {
        return tradeBill;
    }

    public void setTradeBill(TradeBill tradeBill) {
        this.tradeBill = tradeBill;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
