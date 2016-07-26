package com.wlxk.service.tradebill.impl;

import com.wlxk.domain.tradebill.Losses;
import com.wlxk.repository.tradebill.LossesRepository;
import com.wlxk.service.tradebill.LossesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by malin on 2016/7/22.
 */
@Service
public class LossesServiceImpl implements LossesService {
    @Autowired(required = false)
    private LossesRepository lossesRepository;

    @Override
    public Iterable<Losses> save(List<Losses> list) {
        return lossesRepository.save(list);
    }

    @Override
    public List<Losses> getListByTradeBillId(String tradeBillId) {
        return lossesRepository.findByTradeBillId(tradeBillId);
    }

    @Override
    public void deleteListByTradeBillId(String tradeBillId) {
        lossesRepository.deleteByTradeBillId(tradeBillId);
    }
}
