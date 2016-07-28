package com.wlxk.repository.branch;

import com.wlxk.domain.branch.BranchOperationRecord;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 网点操作记录仓储层
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/26
 */
public interface BranchOperationRecordRepository extends PagingAndSortingRepository<BranchOperationRecord, String> {
    List<BranchOperationRecord> findByBusinessId(String businessId);
}
