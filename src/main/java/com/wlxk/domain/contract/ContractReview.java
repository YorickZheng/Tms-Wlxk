package com.wlxk.domain.contract;

import com.wlxk.domain.BasicReview;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 合同审核类
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/26
 */
@Entity
@Table(name = "tms_contract_review")
public class ContractReview extends BasicReview {
    /** 合同ID */
    private String contractId;

    public static ContractReview newDefaultInstance(String contractId) {
        ContractReview review = new ContractReview();
        review.setContractId(contractId);
        review.setStatus(0);
        review.setSort(0);
        return review;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
}
