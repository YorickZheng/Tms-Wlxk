package com.wlxk.repository.contract;

import com.wlxk.domain.contract.Contract;
import com.wlxk.domain.sys.User;
import com.wlxk.support.CustomRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 装车合同仓储层
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/26
 */
public interface ContractRepository extends CustomRepository<Contract, String>, JpaSpecificationExecutor<Contract> {
}
