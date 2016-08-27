package com.wlxk.controller.tradebill;

import com.wlxk.controller.tradebill.vo.*;
import com.wlxk.service.tradebill.TradeBillService;
import com.wlxk.support.exception.TmsDataValidationException;
import com.wlxk.support.util.CommonProperty;
import com.wlxk.support.util.ResultsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 交易单控制器
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/22
 */
@RestController
@RequestMapping("/tradeBill")
public class TradeBillController {
    private static final Logger logger = LoggerFactory.getLogger(TradeBillController.class);

    @Autowired(required = false)
    private TradeBillService tradeBillService;

    /**
     * 开单
     *
     * @param vo 开单数据
     * @return 操作结果
     */
    @RequestMapping(value = "/openTradeBill", method = RequestMethod.POST)
    public Map openTradeBill(@RequestBody OpenTradeBillVo vo) {
        try {
            return tradeBillService.openTradeBill(vo);
        } catch (TmsDataValidationException e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
        }
    }

    /**
     * 交易单审核
     *
     * @param vo 审核数据
     * @return 操作结果
     */
    @RequestMapping(value = "/review", method = RequestMethod.POST)
    public Map reviewTradeBill(@RequestBody ReviewTradeBillVo vo) {
        try {
            return tradeBillService.reviewTradeBill(vo);
        } catch (TmsDataValidationException e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
        }
    }

    /**
     * 交易单修改
     *
     * @param vo 修改数据
     * @return 操作结果
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Map updateTradeBill(@RequestBody UpdateTradeBillVo vo) {
        try {
            return tradeBillService.updateTradeBill(vo);
        } catch (TmsDataValidationException e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
        }
    }

    /**
     * 交易单作废
     *
     * @param vo 作废数据
     * @return 操作结果
     */
    @RequestMapping(value = "/disuse", method = RequestMethod.POST)
    public Map disuse(@RequestBody DisuseTradeBillVo vo) {
        try {
            return tradeBillService.disuseTradeBill(vo);
        } catch (TmsDataValidationException e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
        }
    }

    /**
     * 分页查询交易单
     *
     * @param vo 查询条件
     * @return 分页数据
     */
    @RequestMapping(value = "/pageView", method = RequestMethod.POST)
    public Map pageView(@RequestBody QueryTradeBillVo vo) {
        try {
            return tradeBillService.getTradeBillViewPage(vo);
        } catch (TmsDataValidationException e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
        }
    }

    /**
     * 通过交易单号查询
     *
     * @param tradeBillNo
     * @return
     */
    @RequestMapping(value = "/byTradeBillNo/{tradeBillNo}", method = RequestMethod.GET)
    public Map byTradeBillNo(@PathVariable String tradeBillNo) {
        try {
            return tradeBillService.byTradeBillNo(tradeBillNo);
        } catch (TmsDataValidationException e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
        }
    }

    /**
     * 通过交易单ID查询
     *
     * @param tradeBillId
     * @return
     */
    @RequestMapping(value = "/{tradeBillId}", method = RequestMethod.GET)
    public Map byTradeBillId(@PathVariable String tradeBillId) {
        try {
            return tradeBillService.byTradeBillId(tradeBillId);
        } catch (TmsDataValidationException e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
        }
    }

}
