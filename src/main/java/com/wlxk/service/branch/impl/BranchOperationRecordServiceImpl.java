package com.wlxk.service.branch.impl;

import com.wlxk.domain.branch.BranchOperationRecord;
import com.wlxk.repository.branch.BranchOperationRecordRepository;
import com.wlxk.service.branch.BranchOperationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by malin on 2016/7/26.
 */
@Service
public class BranchOperationRecordServiceImpl implements BranchOperationRecordService {

    @Autowired(required = false)
    private BranchOperationRecordRepository repository;

    @Override
    public BranchOperationRecord save(BranchOperationRecord operationRecord) {
        return repository.save(operationRecord);
    }

    @Override
    public List<BranchOperationRecord> getListByBranchId(String branchId) {
        return repository.findByBusinessId(branchId);
    }
}
