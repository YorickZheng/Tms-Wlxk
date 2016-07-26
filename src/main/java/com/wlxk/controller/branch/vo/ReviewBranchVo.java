package com.wlxk.controller.branch.vo;

/**
 * Created by malin on 2016/7/26.
 */
public class ReviewBranchVo {
    private String branchId;
    private String personalById;
    private String personal;
    private String description;

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getPersonalById() {
        return personalById;
    }

    public void setPersonalById(String personalById) {
        this.personalById = personalById;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
