package com.wlxk.controller.tradebill.vo;

/**
 * Created by malin on 2016/7/24.
 */
public class UpdateTradeBillVo extends OpenTradeBillVo {
    private String tradeBillId;
    private String tradeBillNo;
    private String modifyById;

    public String getTradeBillId() {
        return tradeBillId;
    }

    public void setTradeBillId(String tradeBillId) {
        this.tradeBillId = tradeBillId;
    }

    public String getTradeBillNo() {
        return tradeBillNo;
    }

    public void setTradeBillNo(String tradeBillNo) {
        this.tradeBillNo = tradeBillNo;
    }

    public String getModifyById() {
        return modifyById;
    }

    public void setModifyById(String modifyById) {
        this.modifyById = modifyById;
    }
}
