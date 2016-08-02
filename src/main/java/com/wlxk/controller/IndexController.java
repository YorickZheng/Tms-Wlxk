package com.wlxk.controller;

import com.google.common.base.Strings;
import com.wlxk.controller.sys.UserController;
import com.wlxk.controller.sys.vo.user.UserView;
import com.wlxk.domain.sys.User;
import com.wlxk.service.sys.UserService;
import com.wlxk.support.cache.redis.TmsRedisCache;
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

import javax.websocket.server.PathParam;
import java.util.*;

/**
 * Created by malin on 2016/7/20.
 */
@RestController
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private TmsRedisCache redisCache;

    @RequestMapping(value = "/login")
    public Map<String, Object> login(@RequestBody LoginUser loginUser) {
        try {
            logger.info("1. 数据校验");
            loginByDataValidation(loginUser);

            logger.info("2. 获取缓存");
            String key = CommonProperty.RedisKeyPrefix.USER_PREFIX + loginUser.getUsername();
            if (redisCache.exists(key)) {
                return ResultsUtil.getSuccessResultMap("登录成功!");
            } else {
                logger.info("3. 获取用户信息");
                Map resultMap = userService.getByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword());
                User user = ((UserView) resultMap.get("data")).getUser();
                if (Objects.isNull(user)) {
                    return ResultsUtil.getFailureResultMap("账号密码错误!");
                } else {
                    if (!redisCache.exists(key)) {
                        redisCache.set(key, (UserView) resultMap.get("data"), 3600L);
                    }
                    return ResultsUtil.getSuccessResultMap("登录成功!");
                }
            }
        } catch (TmsDataValidationException e) {
            logger.error(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.DATA_VALIDATION_EXCEPTION);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResultsUtil.getFailureResultMap(e.getMessage(), CommonProperty.HttpCode.EXCEPTION);
        }

    }

    private void loginByDataValidation(LoginUser loginUser) {
        if (Objects.isNull(loginUser)) {
            throw new TmsDataValidationException("请求主体对象不能为空!");
        }
        if (Strings.isNullOrEmpty(loginUser.getUsername())) {
            throw new TmsDataValidationException("用户名不能为空!");
        }
        if (Strings.isNullOrEmpty(loginUser.getPassword())) {
            throw new TmsDataValidationException("密码不能为空!");
        }
    }
}

class LoginUser {
    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
