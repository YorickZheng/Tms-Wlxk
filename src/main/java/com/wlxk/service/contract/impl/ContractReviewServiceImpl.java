package com.wlxk.service.contract.impl;

import com.wlxk.domain.contract.ContractReview;
import com.wlxk.repository.contract.ContractReviewRepository;
import com.wlxk.service.contract.ContractReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by malin on 2016/7/26.
 */
@Service
public class ContractReviewServiceImpl implements ContractReviewService {

    @Autowired(required = false)
    private ContractReviewRepository repository;

    @Override
    public ContractReview save(ContractReview contractReview) {
        return repository.save(contractReview);
    }

    @Override
    public Iterable<ContractReview> save(List<ContractReview> list) {
        return repository.save(list);
    }

    @Override
    public ContractReview getOneByContractId(String contractId) {
        return repository.findOneByBusinessId(contractId);
    }

    @Override
    public List<ContractReview> getListByContractId(String contractId) {
        return repository.findByBusinessId(contractId);
    }
}
