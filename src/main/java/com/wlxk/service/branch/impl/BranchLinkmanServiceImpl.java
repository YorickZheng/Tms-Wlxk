package com.wlxk.service.branch.impl;

import com.wlxk.domain.branch.BranchLinkman;
import com.wlxk.repository.branch.BranchLinkmanRepository;
import com.wlxk.service.branch.BranchLinkmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by malin on 2016/7/26.
 */
@Service
public class BranchLinkmanServiceImpl implements BranchLinkmanService {

    @Autowired(required = false)
    private BranchLinkmanRepository repository;

    @Override
    public Iterable<BranchLinkman> save(List<BranchLinkman> list) {
        return repository.save(list);
    }

    @Override
    public List<BranchLinkman> getListByBranchId(String branchId) {
        return repository.findByBranchId(branchId);
    }

    @Override
    public void deleteListByBranchId(String branchId) {
        repository.deleteByBranchId(branchId);
    }
}
