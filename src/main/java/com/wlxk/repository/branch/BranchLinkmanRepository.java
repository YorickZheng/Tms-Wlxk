package com.wlxk.repository.branch;

import com.wlxk.domain.branch.BranchLinkman;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 网点联系人仓储层
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/26
 */
public interface BranchLinkmanRepository extends PagingAndSortingRepository<BranchLinkman, String> {
    List<BranchLinkman> findByBranchId(String branchId);

    void deleteByBranchId(String branchId);
}
