package com.wlxk.repository.sys;

import com.wlxk.domain.sys.Menu;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 菜单仓储层
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/22
 */
public interface MenuRepository extends PagingAndSortingRepository<Menu, String> {
}
