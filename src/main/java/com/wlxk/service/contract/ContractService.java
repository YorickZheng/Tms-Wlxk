package com.wlxk.service.contract;

import com.wlxk.controller.contract.vo.AddContractVo;
import com.wlxk.controller.contract.vo.DisuseContractVo;
import com.wlxk.controller.contract.vo.ReviewContractVo;
import com.wlxk.controller.contract.vo.UpdateContractVo;
import com.wlxk.domain.contract.Contract;

import java.util.Map;

/**
 * Created by malin on 2016/7/26.
 */
public interface ContractService {
    Contract getOneById(String id);
    Contract save(Contract contract);

    Map add(AddContractVo vo);
    Map review(ReviewContractVo vo);
    Map disuse(DisuseContractVo vo);
    Map update(UpdateContractVo vo);
}
