package com.wlxk.repository.sys;

import com.wlxk.domain.sys.Menu;
import com.wlxk.support.CustomRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 菜单仓储层
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/22
 */
public interface MenuRepository extends PagingAndSortingRepository<Menu, String>, JpaSpecificationExecutor<Menu> {
    List<Menu> findByParentMenuId(String parentMenuId);
    List<Menu> findByIdIn(List<String> idList);
    Menu findOneByCode(String code);
}
