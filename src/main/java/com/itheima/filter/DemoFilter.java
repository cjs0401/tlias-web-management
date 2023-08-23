package com.itheima.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author jsc
 * @version 1.0
 */

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init 方法");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("拦截之前：");
        chain.doFilter(request,response);
        log.info("拦截之后：");

    }

    @Override
    public void destroy() {
        log.info("init 方法");
    }
}
