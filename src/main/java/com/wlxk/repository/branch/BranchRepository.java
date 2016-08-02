package com.wlxk.repository.branch;

import com.wlxk.domain.branch.Branch;
import com.wlxk.support.CustomRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 网点仓储层
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/26
 */
public interface BranchRepository extends PagingAndSortingRepository<Branch, String>, JpaSpecificationExecutor<Branch> {
}
