package com.wlxk.service.branch;

import com.wlxk.domain.branch.BranchReview;

import java.util.List;

/**
 * Created by malin on 2016/7/26.
 */
public interface BranchReviewService {
    BranchReview save(BranchReview branchReview);
    Iterable<BranchReview> save(List<BranchReview> list);
    BranchReview getOneByBranchId(String branchId);
    List<BranchReview> getListByBranchId(String branchId);

}
