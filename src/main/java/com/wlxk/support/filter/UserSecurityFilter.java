package com.wlxk.support.filter;

import com.google.common.base.Strings;
import com.wlxk.controller.sys.vo.user.UserView;
import com.wlxk.domain.sys.Menu;
import com.wlxk.support.cache.redis.TmsRedisCache;
import com.wlxk.support.util.CommonProperty;
import com.wlxk.support.util.JsonUtil;
import com.wlxk.support.util.ResultsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2016/8/1.
 */
// @WebFilter(filterName = "userSecurityFilter", urlPatterns = {"/user/*", "/menu/*"})
public class UserSecurityFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(UserSecurityFilter.class);

    @Autowired
    private TmsRedisCache redisCache;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("userSecurityFilter 加载成功!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        TmsServletRequest tmsServletRequest = null;
        String method = null;
        if (request instanceof HttpServletRequest) {
            tmsServletRequest = new TmsServletRequest((HttpServletRequest) request);
            method = tmsServletRequest.getMethod();
        }
        if (tmsServletRequest == null || method.equals("GET")) {
            chain.doFilter(request, response);
        } else {
            boolean securityResult = security(tmsServletRequest, response);
            if (securityResult) {
                chain.doFilter(tmsServletRequest, response);
            }
        }
    }

    @Override
    public void destroy() {
        System.out.println("userSecurityFilter 销毁!");
    }

    private boolean security(TmsServletRequest request, ServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        logger.debug("1. 获取请求的json");
        String json = TmsServletRequest.getRequestPostStr(request);
        if (Strings.isNullOrEmpty(json)) {
            response.getWriter().println(JsonUtil.obj2Json(ResultsUtil.getFailureResultMap("请求报文异常!")));
            return false;
        }
        logger.debug("2. 解析json");
        Map obj = JsonUtil.json2Map(json);
        String operationByName = (String) obj.get("operationByName");
        if (Strings.isNullOrEmpty(operationByName)) {
            response.getWriter().println(JsonUtil.obj2Json(ResultsUtil.getFailureResultMap("请求报文缺少operationByName字段!")));
            return false;
        }
        logger.debug("3. 获取缓存数据");
        UserView userView = (UserView) redisCache.get(CommonProperty.RedisKeyPrefix.USER_PREFIX + operationByName);
        if (Objects.isNull(userView)) {
            response.getWriter().println(JsonUtil.obj2Json(ResultsUtil.getFailureResultMap("登录超时!")));
            return false;
        }
        if (userView.getMenuList() == null) {
            response.getWriter().println(JsonUtil.obj2Json(ResultsUtil.getFailureResultMap(operationByName + "不拥有" + uri + "的权限")));
            return false;
        }
        Boolean result = userView.getMenuList().stream().map(Menu::getUrl).collect(Collectors.toList()).contains(uri);
        if (!result) {
            response.getWriter().println(JsonUtil.obj2Json(ResultsUtil.getFailureResultMap(operationByName + "不拥有" + uri + "的权限")));
            return false;
        }
        return true;
    }

}
