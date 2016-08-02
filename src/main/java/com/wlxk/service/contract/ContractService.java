package com.wlxk.service.contract;

import com.wlxk.controller.contract.vo.*;
import com.wlxk.domain.contract.Contract;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;
import java.util.Map;

/**
 * Created by malin on 2016/7/26.
 */
@Transactional
public interface ContractService {
    Contract getOneById(String id);
    Contract save(Contract contract);

    Map add(AddContractVo vo);
    Map review(ReviewContractVo vo);
    Map disuse(DisuseContractVo vo);
    Map update(UpdateContractVo vo);

    Page<Contract> pageData(QueryContractVo vo);

    Map pageView(QueryContractVo vo);
}
