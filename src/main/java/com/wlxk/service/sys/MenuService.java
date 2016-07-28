package com.wlxk.service.sys;

import com.wlxk.controller.sys.vo.menu.AddMenuVo;
import com.wlxk.controller.sys.vo.menu.DisuseMenuVo;
import com.wlxk.controller.sys.vo.menu.QueryMenuVo;
import com.wlxk.controller.sys.vo.menu.UpdateMenuVo;
import com.wlxk.controller.sys.vo.user.DisuseUserVo;
import com.wlxk.controller.sys.vo.user.QueryUserVo;
import com.wlxk.domain.sys.Menu;

import java.util.List;
import java.util.Map;

/**
 * Created by malin on 2016/7/28.
 */
public interface MenuService {
    Menu save(Menu menu);
    Iterable<Menu> save(List<Menu> list);
    Menu getOneById(String id);
    List<Menu> getListByParentMenuId(String parentMenuId);

    Map addMenu(AddMenuVo vo);
    Map disuseMenu(DisuseMenuVo vo);
    Map updateMenu(UpdateMenuVo vo);
    Map queryMenu(QueryMenuVo vo);
}
