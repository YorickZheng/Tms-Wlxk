package com.wlxk.repository.sys;

import com.wlxk.domain.sys.RoleMenu;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 角色菜单仓储层
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/22
 */
public interface RoleMenuRepository extends PagingAndSortingRepository<RoleMenu, String> {
    List<RoleMenu> findByRoleId(String roleId);
}
