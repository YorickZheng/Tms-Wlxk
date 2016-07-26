package com.wlxk.controller.branch.vo;

import com.wlxk.domain.branch.Branch;
import com.wlxk.domain.branch.BranchBankCard;
import com.wlxk.domain.branch.BranchLinkman;

import java.util.List;

/**
 * Created by malin on 2016/7/26.
 */
public class UpdateBranchVo {
    private Branch branch;
    private List<BranchBankCard> branchBankCardList;
    private List<BranchLinkman> branchLinkmanList;
    private String description;

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public List<BranchBankCard> getBranchBankCardList() {
        return branchBankCardList;
    }

    public void setBranchBankCardList(List<BranchBankCard> branchBankCardList) {
        this.branchBankCardList = branchBankCardList;
    }

    public List<BranchLinkman> getBranchLinkmanList() {
        return branchLinkmanList;
    }

    public void setBranchLinkmanList(List<BranchLinkman> branchLinkmanList) {
        this.branchLinkmanList = branchLinkmanList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
