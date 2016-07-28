package com.wlxk.controller.tradebill.vo;

import com.wlxk.domain.tradebill.Goods;
import com.wlxk.domain.tradebill.Losses;
import com.wlxk.domain.tradebill.TradeBill;
import com.wlxk.support.vo.BasicOperationVo;

import java.util.List;

/**
 * 开单 Vo
 * Created by malin on 2016/7/22.
 */
public class OpenTradeBillVo extends BasicOperationVo {
    private TradeBill tradeBill;
    private List<Goods> goodsList;
    private List<Losses> lossesList;

    public TradeBill getTradeBill() {
        return tradeBill;
    }

    public void setTradeBill(TradeBill tradeBill) {
        this.tradeBill = tradeBill;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public List<Losses> getLossesList() {
        return lossesList;
    }

    public void setLossesList(List<Losses> lossesList) {
        this.lossesList = lossesList;
    }
}
