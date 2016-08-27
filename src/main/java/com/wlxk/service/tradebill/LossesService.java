package com.wlxk.service.tradebill;

import com.wlxk.controller.tradebill.vo.LossesView;
import com.wlxk.controller.tradebill.vo.QueryLossesVo;
import com.wlxk.domain.tradebill.Losses;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by malin on 2016/7/22.
 */
public interface LossesService {
    /**
     * 批量保存费用
     *
     * @param list
     * @return
     */
    Iterable<Losses> save(List<Losses> list);

    /**
     * 通过交易单ID查询费用
     *
     * @param tradeBillId
     * @return
     */
    List<Losses> getListByTradeBillId(String tradeBillId);

    /**
     * 通过交易单ID删除费用
     *
     * @param tradeBillId
     */
    void deleteListByTradeBillId(String tradeBillId);

    /**
     * 单表多条件分页查询
     *
     * @param vo
     * @return
     */
    Page<Losses> getLossesPage(QueryLossesVo vo);

    /**
     * 多表多条件分页查询
     *
     * @param vo
     * @return
     */
    Map getLossesViewPage(QueryLossesVo vo);
}
