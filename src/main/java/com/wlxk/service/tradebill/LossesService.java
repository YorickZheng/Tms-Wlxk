package com.wlxk.service.tradebill;

import com.wlxk.domain.tradebill.Losses;

import java.util.List;

/**
 * Created by malin on 2016/7/22.
 */
public interface LossesService {
    Iterable<Losses> save(List<Losses> list);
    List<Losses> getListByTradeBillId(String tradeBillId);
    void deleteListByTradeBillId(String tradeBillId);
}
