package com.wlxk.repository.contract;

import com.wlxk.domain.contract.ContractLine;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 装车合同线路仓储层
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/26
 */
public interface ContractLineRepository extends PagingAndSortingRepository<ContractLine, String> {
    List<ContractLine> findByContractId(String contractId);
}
