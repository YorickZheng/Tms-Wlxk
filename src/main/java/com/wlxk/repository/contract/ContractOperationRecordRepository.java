package com.wlxk.repository.contract;

import com.wlxk.domain.contract.ContractOperationRecord;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 装车合同操作记录仓储层
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/26
 */
public interface ContractOperationRecordRepository extends PagingAndSortingRepository<ContractOperationRecord, String> {
}
