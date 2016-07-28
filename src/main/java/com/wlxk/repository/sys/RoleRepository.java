package com.wlxk.repository.sys;

import com.wlxk.domain.sys.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 角色仓储层
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/22
 */
public interface RoleRepository extends PagingAndSortingRepository<Role, String> {
    List<Role> findByIdIn(List<String> idList);
}
