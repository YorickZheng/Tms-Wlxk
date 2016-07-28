package com.wlxk.controller.sys.vo.menu;

import com.wlxk.domain.sys.Menu;
import com.wlxk.support.vo.BasicOperationVo;

/**
 * Created by malin on 2016/7/28.
 */
public class AddMenuVo extends BasicOperationVo {
    private Menu menu;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
