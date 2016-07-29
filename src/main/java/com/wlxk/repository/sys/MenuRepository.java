package com.wlxk.repository.sys;

import com.wlxk.domain.sys.Menu;
import com.wlxk.support.CustomRepository;

import java.util.List;

/**
 * 菜单仓储层
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/22
 */
public interface MenuRepository extends CustomRepository<Menu, String> {
    List<Menu> findByParentMenuId(String parentMenuId);
    List<Menu> findByIdIn(List<String> ids);
}
