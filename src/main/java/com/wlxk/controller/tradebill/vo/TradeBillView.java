package com.wlxk.controller.tradebill.vo;

import com.wlxk.domain.tradebill.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by malin on 2016/7/25.
 */
public class TradeBillView {

    private TradeBill tradeBill;
    private List<Goods> goodsList;
    private List<Losses> lossesList;
    private List<TradeBillReview> tradeBillViewList;
    private List<TradeBillOperationRecord> tradeBillOperationRecordList;

    public static TradeBillView newInstance(TradeBill tradeBill,
                                            List<Goods> goodsList,
                                            List<Losses> lossesList,
                                            List<TradeBillReview> tradeBillViewList,
                                            List<TradeBillOperationRecord> tradeBillOperationRecordList) {
        TradeBillView view = new TradeBillView();
        view.setTradeBill(tradeBill);
        view.setGoodsList(goodsList);
        view.setLossesList(lossesList);
        view.setTradeBillViewList(tradeBillViewList);
        view.setTradeBillOperationRecordList(tradeBillOperationRecordList);
        return view;
    }

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

    public List<TradeBillReview> getTradeBillViewList() {
        return tradeBillViewList;
    }

    public void setTradeBillViewList(List<TradeBillReview> tradeBillViewList) {
        this.tradeBillViewList = tradeBillViewList;
    }

    public List<TradeBillOperationRecord> getTradeBillOperationRecordList() {
        return tradeBillOperationRecordList;
    }

    public void setTradeBillOperationRecordList(List<TradeBillOperationRecord> tradeBillOperationRecordList) {
        this.tradeBillOperationRecordList = tradeBillOperationRecordList;
    }
}
