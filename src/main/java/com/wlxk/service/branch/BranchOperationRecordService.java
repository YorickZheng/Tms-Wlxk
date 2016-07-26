package com.wlxk.service.branch;

import com.wlxk.domain.branch.BranchOperationRecord;

import java.util.List;

/**
 * Created by malin on 2016/7/26.
 */
public interface BranchOperationRecordService {
    BranchOperationRecord save(BranchOperationRecord operationRecord);
    List<BranchOperationRecord> getListByBranchId(String branchId);
}
