package com.wlxk.controller.sys;

import com.wlxk.controller.sys.vo.menu.AddMenuVo;
import com.wlxk.controller.sys.vo.menu.DisuseMenuVo;
import com.wlxk.controller.sys.vo.menu.QueryMenuVo;
import com.wlxk.controller.sys.vo.menu.UpdateMenuVo;
import com.wlxk.service.sys.MenuService;
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
 * Created by malin on 2016/7/28.
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
    @Autowired(required = false)
    private MenuService menuService;

    /**
     * 新增菜单
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map add(@RequestBody AddMenuVo vo) {
        try {
            return menuService.add(vo);
        } catch (TmsDataValidationException e) {
            logger.error(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
        }
    }

    /**
     * 作废菜单
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/disuse", method = RequestMethod.POST)
    public Map disuse(@RequestBody DisuseMenuVo vo) {
        try {
            return menuService.disuse(vo);
        } catch (TmsDataValidationException e) {
            logger.error(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
        }
    }

    /**
     * 更新菜单
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Map update(@RequestBody UpdateMenuVo vo) {
        try {
            return menuService.update(vo);
        } catch (TmsDataValidationException e) {
            logger.error(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
        }
    }

    /**
     * 查询菜单
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public Map query(@RequestBody QueryMenuVo vo) {
        try {
            return menuService.queryMenu(vo);
        } catch (TmsDataValidationException e) {
            logger.error(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
        }
    }
}
