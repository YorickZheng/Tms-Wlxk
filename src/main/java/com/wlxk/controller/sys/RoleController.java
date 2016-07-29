package com.wlxk.controller.sys;

import com.wlxk.controller.sys.vo.role.AddRoleVo;
import com.wlxk.controller.sys.vo.role.DisuseRoleVo;
import com.wlxk.controller.sys.vo.role.QueryRoleVo;
import com.wlxk.controller.sys.vo.role.UpdateRoleVo;
import com.wlxk.service.sys.RoleService;
import com.wlxk.support.exception.TmsDataValidationException;
import com.wlxk.support.util.CommonProperty;
import com.wlxk.support.util.ResultsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by malin on 2016/7/28.
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired(required = false)
    private RoleService roleService;

    /**
     * 新增角色
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map add(@RequestBody AddRoleVo vo) {
        try {
            return roleService.add(vo);
        } catch (TmsDataValidationException e) {
            logger.error(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
        }
    }

    /**
     * 作废角色
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/disuse", method = RequestMethod.POST)
    public Map disuse(@RequestBody DisuseRoleVo vo) {
        try {
            return roleService.disuse(vo);
        } catch (TmsDataValidationException e) {
            logger.error(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
        }
    }

    /**
     * 更新角色
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Map update(@RequestBody UpdateRoleVo vo) {
        try {
            return roleService.update(vo);
        } catch (TmsDataValidationException e) {
            logger.error(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
        }
    }

    /**
     * 分页查询角色视图
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/pageView", method = RequestMethod.POST)
    public Map pageData(@RequestBody QueryRoleVo vo) {
        try {
            return roleService.getPageView(vo);
        } catch (TmsDataValidationException e) {
            logger.error(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
        }
    }


}
