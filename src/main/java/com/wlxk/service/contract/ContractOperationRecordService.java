package com.wlxk.service.contract;

import com.wlxk.domain.contract.ContractOperationRecord;

import java.util.List;

/**
 * Created by malin on 2016/7/26.
 */
public interface ContractOperationRecordService {
    ContractOperationRecord save(ContractOperationRecord operationRecord);
    List<ContractOperationRecord> getListByBusinessId(String businessId);
}
