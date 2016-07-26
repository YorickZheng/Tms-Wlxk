package com.wlxk.repository.sys;

import com.wlxk.domain.sys.UserRole;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 用户角色仓储层
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/22
 */
public interface UserRoleRepository extends PagingAndSortingRepository<UserRole, String> {
}
