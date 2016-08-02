package com.wlxk.controller.contract.vo;

import com.wlxk.domain.contract.Contract;
import com.wlxk.domain.contract.ContractLine;
import com.wlxk.domain.contract.ContractOperationRecord;
import com.wlxk.domain.contract.ContractReview;

import java.util.List;

/**
 * Created by malin on 2016/7/26.
 */
public class ContractView {
    private Contract contract;
    private List<ContractLine> lineList;
    private List<ContractReview> reviews;
    private List<ContractOperationRecord> operationRecordList;

    public static ContractView newInstance(Contract contract,
                                           List<ContractLine> lineList,
                                           List<ContractReview> reviews,
                                           List<ContractOperationRecord> operationRecordList) {
        ContractView view = new ContractView();
        view.setContract(contract);
        view.setLineList(lineList);
        view.setReviews(reviews);
        view.setOperationRecordList(operationRecordList);
        return view;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public List<ContractLine> getLineList() {
        return lineList;
    }

    public void setLineList(List<ContractLine> lineList) {
        this.lineList = lineList;
    }

    public List<ContractReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<ContractReview> reviews) {
        this.reviews = reviews;
    }

    public List<ContractOperationRecord> getOperationRecordList() {
        return operationRecordList;
    }

    public void setOperationRecordList(List<ContractOperationRecord> operationRecordList) {
        this.operationRecordList = operationRecordList;
    }
}
