package com.wlxk.controller.tradebill.vo;

/**
 * 交易单审核 Vo
 * Created by malin on 2016/7/24.
 */
public class ReviewTradeBillVo {
    private String tradeBillId;
    private String reviewPerson;
    private String description;
    private String modifyById;
    private Integer command;

    public String getTradeBillId() {
        return tradeBillId;
    }

    public void setTradeBillId(String tradeBillId) {
        this.tradeBillId = tradeBillId;
    }

    public String getReviewPerson() {
        return reviewPerson;
    }

    public void setReviewPerson(String reviewPerson) {
        this.reviewPerson = reviewPerson;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModifyById() {
        return modifyById;
    }

    public void setModifyById(String modifyById) {
        this.modifyById = modifyById;
    }

    public Integer getCommand() {
        return command;
    }

    public void setCommand(Integer command) {
        this.command = command;
    }
}
