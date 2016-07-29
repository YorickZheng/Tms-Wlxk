package com.wlxk.service.sys;

import com.wlxk.controller.sys.vo.menu.AddMenuVo;
import com.wlxk.controller.sys.vo.menu.DisuseMenuVo;
import com.wlxk.controller.sys.vo.menu.QueryMenuVo;
import com.wlxk.controller.sys.vo.menu.UpdateMenuVo;
import com.wlxk.domain.sys.Menu;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by malin on 2016/7/28.
 */
@Transactional
public interface MenuService {
    Menu save(Menu menu);

    Iterable<Menu> save(List<Menu> list);

    Menu getOneById(String id);

    List<Menu> getListById(List<String> ids);

    List<Menu> getListByParentMenuId(String parentMenuId);

    Map add(AddMenuVo vo);

    Map disuse(DisuseMenuVo vo);

    Map update(UpdateMenuVo vo);

    Map queryMenu(QueryMenuVo vo);


}
