package com.wlxk.support.util;

import org.springframework.data.domain.*;

import java.util.List;

/**
 * Created by malin on 2016/7/26.
 */
public class PageUtil<T> {
    public static Page newInstancePage(Page resourcePage, List content) {
        Pageable pageable = new PageRequest(resourcePage.getNumber(), resourcePage.getSize(), resourcePage.getSort());
        Page page = new PageImpl(content, pageable, resourcePage.getTotalElements());
        return page;
    }
}
