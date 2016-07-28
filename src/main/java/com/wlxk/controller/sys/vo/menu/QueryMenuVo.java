package com.wlxk.controller.sys.vo.menu;

import com.wlxk.domain.sys.Menu;
import com.wlxk.support.vo.BasicOperationVo;

/**
 * Created by malin on 2016/7/28.
 */
public class QueryMenuVo {
    private String menuId;
    private String parentMenuId;
    private Menu menu;
    private int page;
    private int size;
    private Integer command;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Integer getCommand() {
        return command;
    }

    public void setCommand(Integer command) {
        this.command = command;
    }
}
