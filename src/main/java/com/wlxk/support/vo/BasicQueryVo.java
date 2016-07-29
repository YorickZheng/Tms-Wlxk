package com.wlxk.support.vo;

import com.google.common.collect.Lists;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

/**
 * Created by malin on 2016/7/29.
 */
public class BasicQueryVo {
    private Map<String, Object> params;
    private Integer page = 0;
    private Integer size = 10;
    private Sort.Direction direction = Sort.Direction.DESC;
    private List<String> sortList = Lists.newArrayList("createByDate");

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Sort.Direction getDirection() {
        return direction;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }

    public List<String> getSortList() {
        return sortList;
    }

    public void setSortList(List<String> sortList) {
        this.sortList = sortList;
    }
}
