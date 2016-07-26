package com.wlxk.repository.branch;

import com.wlxk.domain.branch.BranchBankCard;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 网点银行仓储层
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/26
 */
public interface BranchBankCardRepository extends PagingAndSortingRepository<BranchBankCard, String> {
    List<BranchBankCard> findByBranchId(String branchId);
    void deleteByBranchId(String branchId);
}
