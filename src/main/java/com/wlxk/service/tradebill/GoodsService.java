package com.wlxk.service.tradebill;

import com.wlxk.domain.tradebill.Goods;

import java.util.List;

/**
 * Created by malin on 2016/7/22.
 */
public interface GoodsService {
    Iterable<Goods> save(List<Goods> list);
    List<Goods> getListByTradeBillId(String tradeBillId);
    void deleteListByTradeBillId(String tradeBillId);
}
