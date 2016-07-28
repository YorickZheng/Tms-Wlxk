package com.wlxk.controller.sys;

import com.wlxk.controller.sys.vo.user.AddUserVo;
import com.wlxk.controller.sys.vo.user.DisuseUserVo;
import com.wlxk.controller.sys.vo.user.QueryUserVo;
import com.wlxk.controller.sys.vo.user.UpdateUserVo;
import com.wlxk.service.sys.UserService;
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
 * 用户管理控制器
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/27
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired(required = false)
    private UserService userService;

    /**
     * 添加用户
     *
     * @param vo 请求数据
     * @return 结果
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map add(@RequestBody AddUserVo vo) {
        try {
            return userService.add(vo);
        } catch (TmsDataValidationException e) {
            logger.error(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
        }
    }

    /**
     * 作废用户
     *
     * @param vo 请求数据
     * @return 结果
     */
    @RequestMapping(value = "/disuse", method = RequestMethod.POST)
    public Map disuse(@RequestBody DisuseUserVo vo) {
        try {
            return userService.disuse(vo);
        } catch (TmsDataValidationException e) {
            logger.error(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
        }
    }

    /**
     * 更新用户
     *
     * @param vo 请求数据
     * @return 结果
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Map update(@RequestBody UpdateUserVo vo) {
        try {
            return userService.update(vo);
        } catch (TmsDataValidationException e) {
            logger.error(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
        }
    }

    /**
     * 分页查询用户
     *
     * @param vo 请求数据
     * @return 结果
     */
    @RequestMapping(value = "/pageData", method = RequestMethod.POST)
    public Map userViewPage(@RequestBody QueryUserVo vo) {
        try {
            return userService.getUserViewPage(userService.getUserPage(vo, vo.getPage(), vo.getSize()));
        } catch (TmsDataValidationException e) {
            logger.error(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
        }
    }

}
