package com.wlxk.controller.sys.vo.menu;

import com.wlxk.domain.sys.Menu;
import com.wlxk.support.vo.OperationVo;

/**
 * Created by malin on 2016/7/28.
 */
public class AddMenuVo extends OperationVo {
    private Menu menu;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
