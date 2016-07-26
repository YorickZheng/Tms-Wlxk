package com.wlxk.repository.tradebill;

import com.wlxk.domain.tradebill.Goods;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 商品仓储层
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/22
 */
public interface GoodsRepository extends PagingAndSortingRepository<Goods, String> {
    List<Goods> findByTradeBillId(String tradeBillId);
    void deleteByTradeBillId(String tradeBillId);
}
