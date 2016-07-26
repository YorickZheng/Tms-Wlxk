package com.wlxk.service.branch.impl;

import com.wlxk.domain.branch.BranchBankCard;
import com.wlxk.repository.branch.BranchBankCardRepository;
import com.wlxk.service.branch.BranchBankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by malin on 2016/7/26.
 */
@Service
public class BranchBankCardServiceImpl implements BranchBankCardService {

    @Autowired(required = false)
    private BranchBankCardRepository repository;

    @Override
    public Iterable<BranchBankCard> save(List<BranchBankCard> list) {
        return repository.save(list);
    }

    @Override
    public List<BranchBankCard> getListByBranchId(String branchId) {
        return repository.findByBranchId(branchId);
    }

    @Override
    public void deleteListByBranchId(String branchId) {
        repository.deleteByBranchId(branchId);
    }
}
