package com.wlxk.controller.branch;

import com.wlxk.controller.branch.vo.*;
import com.wlxk.domain.branch.Branch;
import com.wlxk.service.branch.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by malin on 2016/7/26.
 */
@RestController
@RequestMapping("/branch")
public class BranchController {
    @Autowired(required = false)
    private BranchService branchService;

    /**
     * 新增网点
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map add(@RequestBody AddBranchVo vo) {
        try {
            return branchService.add(vo);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 作废网点
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/disuse", method = RequestMethod.POST)
    public Map disuse(@RequestBody DisuseBranchVo vo) {
        try {
            return branchService.disuse(vo);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 修改网点
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Map update(@RequestBody UpdateBranchVo vo) {
        try {
            return branchService.update(vo);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 审核网点
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/review", method = RequestMethod.POST)
    public Map review(@RequestBody ReviewBranchVo vo) {
        try {
            return branchService.review(vo);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 分页查询网点
     *
     * @param vo 查询条件
     * @return 查询结果
     */
    @RequestMapping(value = "/getBranchPage", method = RequestMethod.POST)
    public Page<BranchView> getBranchPage(@RequestBody QueryBranchVo vo) {
        try {
            Page<Branch> page = branchService.getBranchPage(vo.getBranch(), vo.getPage(), vo.getSize());
            return branchService.getBranchViewPage(page);
        } catch (Exception e){
            throw e;
        }
    }
}
