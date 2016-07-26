package com.wlxk.service.contract.impl;

import com.wlxk.domain.contract.ContractOperationRecord;
import com.wlxk.repository.contract.ContractOperationRecordRepository;
import com.wlxk.service.contract.ContractOperationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by malin on 2016/7/26.
 */
@Service
public class ContractOperationRecordServiceImpl implements ContractOperationRecordService {

    @Autowired(required = false)
    private ContractOperationRecordRepository repository;

    @Override
    public ContractOperationRecord save(ContractOperationRecord operationRecord) {
        return repository.save(operationRecord);
    }
}
