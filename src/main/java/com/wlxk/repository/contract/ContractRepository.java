package com.wlxk.repository.contract;

import com.wlxk.domain.contract.Contract;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 装车合同仓储层
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/26
 */
public interface ContractRepository extends PagingAndSortingRepository<Contract, String> {
}
