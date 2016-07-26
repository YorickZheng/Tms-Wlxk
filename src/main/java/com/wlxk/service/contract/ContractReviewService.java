package com.wlxk.service.contract;

import com.wlxk.domain.contract.ContractReview;

import java.util.List;

/**
 * Created by malin on 2016/7/26.
 */
public interface ContractReviewService {
    ContractReview save(ContractReview contractReview);
    Iterable<ContractReview> save(List<ContractReview> list);
    ContractReview getOneByContractId(String contractId);
    List<ContractReview> getListByContractId(String contractId);
}
