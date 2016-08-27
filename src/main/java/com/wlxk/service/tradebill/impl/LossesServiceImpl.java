package com.wlxk.service.tradebill.impl;

import com.google.common.collect.Lists;
import com.wlxk.controller.tradebill.vo.LossesView;
import com.wlxk.controller.tradebill.vo.QueryLossesVo;
import com.wlxk.controller.tradebill.vo.TradeBillView;
import com.wlxk.domain.tradebill.Losses;
import com.wlxk.repository.tradebill.LossesRepository;
import com.wlxk.repository.tradebill.specs.LossesSpecs;
import com.wlxk.repository.tradebill.specs.TradeBillSpecs;
import com.wlxk.service.tradebill.LossesService;
import com.wlxk.support.util.PageUtil;
import com.wlxk.support.util.ResultsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by malin on 2016/7/22.
 */
@Service
public class LossesServiceImpl implements LossesService {
    @Autowired
    private LossesRepository repository;

    @Override
    public Iterable<Losses> save(List<Losses> list) {
        return repository.save(list);
    }

    @Override
    public List<Losses> getListByTradeBillId(String tradeBillId) {
        return repository.findByTradeBillId(tradeBillId);
    }

    @Override
    public void deleteListByTradeBillId(String tradeBillId) {
        repository.deleteByTradeBillId(tradeBillId);
    }

    @Override
    public Page<Losses> getLossesPage(QueryLossesVo vo) {
        PageRequest request = new PageRequest(vo.getPage(), vo.getSize(), new Sort(vo.getDirection(), vo.getSortList()));
        return repository.findAll(LossesSpecs.lossesPageSpecs(vo.getParams()), request);
    }

    @Override
    public Map getLossesViewPage(QueryLossesVo vo) {
        Page<Losses> lossesPage = getLossesPage(vo);

        List<LossesView> content = Lists.newArrayList();
        lossesPage.getContent().forEach(losses -> {
            content.add(LossesView.newInstance(losses));
        });

        return ResultsUtil.getSuccessResultMap(PageUtil.newInstancePage(lossesPage, content));
    }
}
