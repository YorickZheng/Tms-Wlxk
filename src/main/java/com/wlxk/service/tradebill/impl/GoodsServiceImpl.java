package com.wlxk.service.tradebill.impl;

import com.wlxk.domain.tradebill.Goods;
import com.wlxk.repository.tradebill.GoodsRepository;
import com.wlxk.service.tradebill.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by malin on 2016/7/22.
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired(required = false)
    private GoodsRepository goodsRepository;

    @Override
    public Iterable<Goods> save(List<Goods> list) {
        return goodsRepository.save(list);
    }

    @Override
    public List<Goods> getListByTradeBillId(String tradeBillId) {
        return goodsRepository.findByTradeBillId(tradeBillId);
    }

    @Override
    public void deleteListByTradeBillId(String tradeBillId) {
        goodsRepository.deleteByTradeBillId(tradeBillId);
    }
}
