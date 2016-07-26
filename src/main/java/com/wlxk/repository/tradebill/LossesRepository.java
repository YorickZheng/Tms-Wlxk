package com.wlxk.repository.tradebill;

import com.wlxk.domain.tradebill.Losses;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 挂账仓储层
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/22
 */
public interface LossesRepository extends PagingAndSortingRepository<Losses, String> {
    List<Losses> findByTradeBillId(String tradeBillId);
    void deleteByTradeBillId(String tradeBillId);
}
