package com.wlxk.controller.branch.vo;

import com.wlxk.domain.branch.Branch;

/**
 * Created by malin on 2016/7/26.
 */
public class QueryBranchVo {
    private Branch branch;
    private Integer page;
    private Integer size;

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
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
