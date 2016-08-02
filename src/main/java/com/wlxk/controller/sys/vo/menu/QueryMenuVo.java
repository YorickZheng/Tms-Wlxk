package com.wlxk.controller.sys.vo.menu;

import com.wlxk.domain.sys.Menu;
import com.wlxk.support.vo.BasicOperationVo;

/**
 * Created by malin on 2016/7/28.
 */
public class QueryMenuVo {
    private String menuId;
    private Integer command;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public Integer getCommand() {
        return command;
    }

    public void setCommand(Integer command) {
        this.command = command;
    }
}
