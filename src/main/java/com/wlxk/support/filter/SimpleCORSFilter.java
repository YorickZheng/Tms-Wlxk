package com.wlxk.support.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by malin on 2016/8/5.
 */
@Component
public class SimpleCORSFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(SimpleCORSFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("跨域Filter 初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("跨域请求的IP:{} \t {}", servletRequest.getRemoteAddr(), servletRequest.getRemoteHost());
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("跨域Filter 销毁");
    }
}
