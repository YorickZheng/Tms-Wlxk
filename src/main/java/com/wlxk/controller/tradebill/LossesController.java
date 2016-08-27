package com.wlxk.controller.tradebill;

import com.wlxk.controller.tradebill.vo.QueryLossesVo;
import com.wlxk.service.tradebill.LossesService;
import com.wlxk.support.exception.TmsDataValidationException;
import com.wlxk.support.util.CommonProperty;
import com.wlxk.support.util.ResultsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by 马林 on 2016/8/27.
 */

@RestController
@RequestMapping("/losses")
public class LossesController {
    private static final Logger logger = LoggerFactory.getLogger(LossesController.class);
    @Autowired
    private LossesService lossesService;

    /**
     * 分页查询费用
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/pageView", method = RequestMethod.POST)
    public Map pageView(@RequestBody QueryLossesVo vo) {
        try {
            return lossesService.getLossesViewPage(vo);
        } catch (TmsDataValidationException e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
        }
    }
}
