package com.wlxk.service.tradebill;

import com.wlxk.controller.tradebill.vo.*;
import com.wlxk.domain.tradebill.TradeBill;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * Created by malin on 2016/7/22.
 */
public interface TradeBillService {
    /**
     * 新增交易单
     *
     * @param tradeBill
     * @return
     */
    TradeBill save(TradeBill tradeBill);

    /**
     * 通过Id查询交易单
     *
     * @param tradeBillId
     * @return
     */
    TradeBill getOneById(String tradeBillId);

    /**
     * 开单
     *
     * @param vo
     * @return
     */
    Map openTradeBill(OpenTradeBillVo vo);

    /**
     * 交易单审核
     *
     * @param vo
     * @param command  
     * @return
     */
    Map reviewTradeBill(ReviewTradeBillVo vo, Integer command);

    /**
     * 交易单修改
     *
     * @param vo
     * @return
     */
    Map updateTradeBill(UpdateTradeBillVo vo);

    /**
     * 作废交易单
     *
     * @param vo
     * @return
     */
    Map disuseTradeBill(DisuseTradeBillVo vo);

    /**
     * 单表多条件分页查询
     *
     * @param tradeBill
     * @param page
     * @param size
     * @return
     */
    Page<TradeBill> getTradeBillPage(TradeBill tradeBill, Integer page, Integer size);

    /**
     * 多表多条件分页查询
     *
     * @param tradeBillPage
     * @return
     */
    Page<TradeBillView> getTradeBillViewPage(Page<TradeBill> tradeBillPage);
}
