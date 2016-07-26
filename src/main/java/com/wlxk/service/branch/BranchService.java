package com.wlxk.service.branch;

import com.wlxk.controller.branch.vo.*;
import com.wlxk.domain.branch.Branch;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * Created by malin on 2016/7/26.
 */
public interface BranchService {

    /**
     * 保存网点
     *
     * @param branch
     * @return
     */
    Branch save(Branch branch);

    /**
     * 通过编号查询网点
     *
     * @param id
     * @return
     */
    Branch getOneById(String id);

    /**
     * 添加网点
     *
     * @param vo 网点数据
     * @return 结果
     */
    Map add(AddBranchVo vo);

    /**
     * 修改网点
     *
     * @param vo 网点修改数据
     * @return 结果
     */
    Map update(UpdateBranchVo vo);

    /**
     * 作废网点
     *
     * @param vo
     * @return
     */
    Map disuse(DisuseBranchVo vo);

    /**
     * 审核网点
     *
     * @param vo
     * @return
     */
    Map review(ReviewBranchVo vo);

    /**
     * 单表多条件分页查询
     *
     * @param branch
     * @param page
     * @param size
     * @return
     */
    Page<Branch> getBranchPage(Branch branch, Integer page, Integer size);

    /**
     * 多表多条件分页查询
     *
     * @param branchPage
     * @return
     */
    Page<BranchView> getBranchViewPage(Page<Branch> branchPage);
}
