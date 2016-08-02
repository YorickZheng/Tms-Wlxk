package com.wlxk.controller;

import com.wlxk.TmsApplication;
import com.wlxk.controller.tradebill.TradeBillController;
import com.wlxk.domain.sys.User;
import com.wlxk.service.sys.UserService;
import com.wlxk.support.cache.redis.TmsRedisCache;
import com.wlxk.support.util.CommonProperty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TmsApplication.class)
@WebAppConfiguration
public class IndexControllerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexControllerTest.class);

    @Autowired
    UserService userService;
    @Autowired
    TmsRedisCache redisCache;

    @Test
    public void testLoginRedis() {
        User user = null;
        String username = "tianye001";
        String password = "123456";

        String key = CommonProperty.RedisKeyPrefix.USER_PREFIX + username;
        if (redisCache.exists(key)) {
            user = (User) redisCache.get(key);
        } else {
            user = userService.findByUsernameAndPassword(username, password);
            redisCache.set(key, user, 100L);
        }
    }
}
