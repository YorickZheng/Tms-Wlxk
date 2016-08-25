package com.wlxk.controller.contract;

import com.wlxk.controller.contract.vo.*;
import com.wlxk.controller.tradebill.TradeBillController;
import com.wlxk.service.contract.ContractService;
import com.wlxk.support.exception.TmsDataValidationException;
import com.wlxk.support.util.CommonProperty;
import com.wlxk.support.util.ResultsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by malin on 2016/7/26.
 */
@RestController
@RequestMapping("/contract")
public class ContractController {
    private static final Logger logger = LoggerFactory.getLogger(ContractController.class);

    @Autowired(required = false)
    private ContractService contractService;

    /**
     * 新增合同
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map add(@RequestBody AddContractVo vo) {
        try {
            return contractService.add(vo);
        } catch (TmsDataValidationException e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
        }
    }

    /**
     * 装车
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/loading", method = RequestMethod.POST)
    public Map loading(@RequestBody LoadingVo vo) {
        try {
            return contractService.loading(vo);
        } catch (TmsDataValidationException e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
        }
    }

    /**
     * 审核合同
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/review", method = RequestMethod.POST)
    public Map review(@RequestBody ReviewContractVo vo) {
        try {
            return contractService.review(vo);
        } catch (TmsDataValidationException e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
        }
    }

    /**
     * 作废合同
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/disuse", method = RequestMethod.POST)
    public Map disuse(@RequestBody DisuseContractVo vo) {
        try {
            return contractService.disuse(vo);
        } catch (TmsDataValidationException e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
        }
    }

    /**
     * 修改合同
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Map update(@RequestBody UpdateContractVo vo) {
        try {
            return contractService.update(vo);
        } catch (TmsDataValidationException e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
        }
    }

    /**
     * 视图分页
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/pageView", method = RequestMethod.POST)
    public Map pageView(@RequestBody QueryContractVo vo) {
        try {
            return contractService.pageView(vo);
        } catch (TmsDataValidationException e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
        }
    }
}
