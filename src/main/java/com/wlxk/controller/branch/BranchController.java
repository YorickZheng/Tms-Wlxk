package com.wlxk.controller.branch;

import com.wlxk.controller.branch.vo.*;
import com.wlxk.controller.contract.ContractController;
import com.wlxk.domain.branch.Branch;
import com.wlxk.service.branch.BranchService;
import com.wlxk.support.exception.TmsDataValidationException;
import com.wlxk.support.util.CommonProperty;
import com.wlxk.support.util.ResultsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(BranchController.class);

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
        } catch (TmsDataValidationException e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
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
        } catch (TmsDataValidationException e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
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
        } catch (TmsDataValidationException e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
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
        } catch (TmsDataValidationException e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
        }
    }

    /**
     * 分页查询网点
     *
     * @param vo 查询条件
     * @return 查询结果
     */
    @RequestMapping(value = "/pageView", method = RequestMethod.POST)
    public Map pageView(@RequestBody QueryBranchVo vo) {
        try {
            return branchService.pageDataView(vo);
        } catch (TmsDataValidationException e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
        }
    }
}
