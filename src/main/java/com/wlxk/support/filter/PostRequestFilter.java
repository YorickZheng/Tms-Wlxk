package com.wlxk.support.filter;

import com.google.common.collect.Lists;
import com.wlxk.support.util.CommonProperty;
import com.wlxk.support.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/8/3.
 */
// @WebFilter(filterName = "userSecurityFilter", urlPatterns = {"/user/*", "/menu/*"})
public class PostRequestFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(PostRequestFilter.class);

    private static final List<String> EXPRESSION_COMMON_LIST = Lists.newArrayList();
    private static final List<String> expression_disuse_list = Lists.newArrayList("d");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("初始化 post request 过滤器, 用于校验post请求的请求数据");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        TmsServletRequest tmsServletRequest = null;
        if (request instanceof HttpServletRequest) {
            String method, uri, json;
            tmsServletRequest = new TmsServletRequest((HttpServletRequest) request);
            method = tmsServletRequest.getMethod();

            if (method.equals("POST")) {
                json = TmsServletRequest.getRequestPostStr(tmsServletRequest);
                if (!json.contains(CommonProperty.PostNotNullAttributes.operationById)) {

                }

                uri = tmsServletRequest.getRequestURI();
                if (EXPRESSION_COMMON_LIST.contains(uri)) {

                }
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

}
