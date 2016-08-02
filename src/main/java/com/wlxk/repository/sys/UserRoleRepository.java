package com.wlxk.repository.sys;

import com.wlxk.domain.sys.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 用户角色仓储层
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/22
 */
public interface UserRoleRepository extends CrudRepository<UserRole, String> {
    List<UserRole> findByUserId(String userId);
    void deleteByUserId(String userId);
}
