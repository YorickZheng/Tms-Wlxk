package com.wlxk.repository.contract;

import com.wlxk.domain.contract.ContractReview;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 装车合同审核记录仓储层
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/26
 */
public interface ContractReviewRepository extends PagingAndSortingRepository<ContractReview, String> {
    ContractReview findOneByContractId(String contractId);
    List<ContractReview> findByContractId(String contractId);
}
