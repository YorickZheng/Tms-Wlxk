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
    /**
     * 通过ID查询
     *
     * @param id
     * @return
     */
    Contract getOneById(String id);

    /**
     * 保存合同
     *
     * @param contract
     * @return
     */
    Contract save(Contract contract);

    /**
     * 新增
     *
     * @param vo
     * @return
     */
    Map add(AddContractVo vo);

    /**
     * 装车
     *
     * @param vo
     * @return
     */
    Map loading(LoadingVo vo);

    /**
     * 审核
     *
     * @param vo
     * @return
     */
    Map review(ReviewContractVo vo);

    /**
     * 作废
     *
     * @param vo
     * @return
     */
    Map disuse(DisuseContractVo vo);

    /**
     * 更新
     *
     * @param vo
     * @return
     */
    Map update(UpdateContractVo vo);

    /**
     * 分页查询合同
     *
     * @param vo
     * @return
     */
    Page<Contract> pageData(QueryContractVo vo);

    /**
     * 分页查询合同视图
     *
     * @param vo
     * @return
     */
    Map pageView(QueryContractVo vo);
}
