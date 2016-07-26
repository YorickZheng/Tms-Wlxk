package com.wlxk.controller.tradebill;

import com.wlxk.controller.tradebill.vo.*;
import com.wlxk.domain.tradebill.TradeBill;
import com.wlxk.support.exception.TmsException;
import com.wlxk.service.tradebill.TradeBillService;
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
 * 交易单控制器
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/22
 */
@RestController
@RequestMapping("/tradeBill")
public class TradeBillController {
    private final static Logger logger = LoggerFactory.getLogger(TradeBillController.class);
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
            tradeBillService.openTradeBill(vo);
        } catch (TmsException e) {
            logger.error("开单失败!", e);
            return ResultsUtil.getFailureResultMap("开单失败!");
        }
        return ResultsUtil.getSuccessResultMap("开单成功!");
    }

    /**
     * 交易单审核
     *
     * @param vo 审核数据
     * @return 操作结果
     */
    @RequestMapping(value = "/reviewTradeBill", method = RequestMethod.POST)
    public Map reviewTradeBill(@RequestBody ReviewTradeBillVo vo) {
        try {
            tradeBillService.reviewTradeBill(vo, vo.getCommand());
        } catch (TmsException e) {
            logger.error("审核失败!", e);
            return ResultsUtil.getFailureResultMap("审核失败!");
        }
        return ResultsUtil.getSuccessResultMap("审核成功!");
    }

    /**
     * 交易单修改
     *
     * @param vo 修改数据
     * @return  操作结果
     */
    @RequestMapping(value = "/updateTradeBill", method = RequestMethod.POST)
    public Map updateTradeBill(@RequestBody UpdateTradeBillVo vo) {
        try {
            tradeBillService.updateTradeBill(vo);
        } catch (TmsException e) {
            logger.error("修改失败!", e);
            return ResultsUtil.getFailureResultMap("修改失败!");
        }
        return ResultsUtil.getSuccessResultMap("修改成功!");
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
            tradeBillService.disuseTradeBill(vo);
        } catch (TmsException e) {
            logger.error("作废失败!", e);
            return ResultsUtil.getFailureResultMap("作废失败!");
        }
        return ResultsUtil.getSuccessResultMap("作废成功!");
    }

    /**
     * 分页查询交易单
     *
     * @param vo 查询条件
     * @return 分页数据
     */
    @RequestMapping(value = "/getTradeBillPage", method = RequestMethod.POST)
    public Page<TradeBillView> getTradeBillPage(@RequestBody QueryTradeBillVo vo) {
        Page<TradeBill> page = tradeBillService.getTradeBillPage(vo.getTradeBill(), vo.getPage(), vo.getSize());
        return tradeBillService.getTradeBillViewPage(page);
    }

}
