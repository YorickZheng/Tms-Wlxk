package com.wlxk.service.tradebill;

import com.wlxk.controller.tradebill.vo.*;
import com.wlxk.domain.tradebill.TradeBill;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by malin on 2016/7/22.
 */
public interface TradeBillService {
    /**
     * 保存交易单
     *
     * @param tradeBill
     * @return
     */
    TradeBill save(TradeBill tradeBill);

    /**
     * 批量保存交易单
     *
     * @param list
     */
    void save(List<TradeBill> list);

    /**
     * 通过Id查询交易单
     *
     * @param tradeBillId
     * @return
     */
    TradeBill getOneById(String tradeBillId);

    /**
     * 通过交易单NO查询交易单
     *
     * @param tradeBillNO
     * @return
     */
    TradeBill getOneByTradeBillNo(String tradeBillNO);

    /**
     * 通过ID集合查询交易单
     *
     * @param idList
     * @return
     */
    List<TradeBill> getListByIdList(List<String> idList);

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
     * @return
     */
    Map reviewTradeBill(ReviewTradeBillVo vo);

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
     * @param vo
     * @return
     */
    Page<TradeBill> getTradeBillPage(QueryTradeBillVo vo);

    /**
     * 多表多条件分页查询
     *
     * @param vo
     * @return
     */
    Map getTradeBillViewPage(QueryTradeBillVo vo);

    /**
     * 通过交易单No查询详情
     *
     * @param tradeBillNo
     * @return
     */
    Map byTradeBillNo(String tradeBillNo);
}
