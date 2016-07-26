package com.wlxk.service.branch;

import com.wlxk.domain.branch.BranchBankCard;

import java.util.List;

/**
 * Created by malin on 2016/7/26.
 */
public interface BranchBankCardService {
    Iterable<BranchBankCard> save(List<BranchBankCard> list);
    List<BranchBankCard> getListByBranchId(String branchId);
    void deleteListByBranchId(String branchId);
}
