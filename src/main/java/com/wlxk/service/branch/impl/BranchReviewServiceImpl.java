package com.wlxk.service.branch.impl;

import com.wlxk.domain.branch.BranchReview;
import com.wlxk.repository.branch.BranchReviewRepository;
import com.wlxk.service.branch.BranchReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by malin on 2016/7/26.
 */
@Service
public class BranchReviewServiceImpl implements BranchReviewService {
    @Autowired(required = false)
    private BranchReviewRepository repository;

    @Override
    public BranchReview save(BranchReview branchReview) {
        return repository.save(branchReview);
    }

    @Override
    public Iterable<BranchReview> save(List<BranchReview> list) {
        return repository.save(list);
    }

    @Override
    public BranchReview getOneByBranchId(String branchId) {
        return repository.findOneByBranchId(branchId);
    }

    @Override
    public List<BranchReview> getListByBranchId(String branchId) {
        return repository.findByBranchId(branchId);
    }
}
