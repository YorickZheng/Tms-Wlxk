package com.wlxk.repository.branch;

import com.wlxk.domain.branch.BranchReview;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 网点审核记录仓储层
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/26
 */
public interface BranchReviewRepository extends PagingAndSortingRepository<BranchReview, String> {
    BranchReview findOneByBranchId(String branchId);
    List<BranchReview> findByBranchId(String branchId);
}
