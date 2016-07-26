package com.wlxk.service.branch;

import com.wlxk.domain.branch.BranchLinkman;

import java.util.List;

/**
 * Created by malin on 2016/7/26.
 */
public interface BranchLinkmanService {
    Iterable<BranchLinkman> save(List<BranchLinkman> list);
    List<BranchLinkman> getListByBranchId(String branchId);
    void deleteListByBranchId(String branchId);
}
