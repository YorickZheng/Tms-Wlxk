package com.wlxk.controller.branch.vo;

import com.wlxk.domain.branch.*;

import java.util.List;

/**
 * Created by malin on 2016/7/26.
 */
public class BranchView {
    private Branch branch;
    private List<BranchBankCard> bankCardList;
    private List<BranchLinkman> linkmenList;
    private List<BranchOperationRecord> operationRecordList;
    private List<BranchReview> reviewList;

    public static BranchView newInstance(Branch branch,
                                         List<BranchBankCard> bankCardList,
                                         List<BranchLinkman> linkmenList,
                                         List<BranchOperationRecord> operationRecordList,
                                         List<BranchReview> reviewList) {
        BranchView view = new BranchView();
        view.setBranch(branch);
        view.setBankCardList(bankCardList);
        view.setLinkmenList(linkmenList);

        return view;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public List<BranchBankCard> getBankCardList() {
        return bankCardList;
    }

    public void setBankCardList(List<BranchBankCard> bankCardList) {
        this.bankCardList = bankCardList;
    }

    public List<BranchLinkman> getLinkmenList() {
        return linkmenList;
    }

    public void setLinkmenList(List<BranchLinkman> linkmenList) {
        this.linkmenList = linkmenList;
    }

    public List<BranchOperationRecord> getOperationRecordList() {
        return operationRecordList;
    }

    public void setOperationRecordList(List<BranchOperationRecord> operationRecordList) {
        this.operationRecordList = operationRecordList;
    }

    public List<BranchReview> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<BranchReview> reviewList) {
        this.reviewList = reviewList;
    }
}
