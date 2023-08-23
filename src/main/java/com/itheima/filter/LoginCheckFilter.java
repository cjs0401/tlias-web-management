package com.itheima.filter;

import com.alibaba.fastjson2.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @author jsc
 * @version 1.0
 */
@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化方法...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String url = httpServletRequest.getRequestURL().toString();
        log.info("请求的url：{}",url);

        if (url.contains("login")) {
            log.info("登录请求 放行：{}",url);
            chain.doFilter(request,response);
            return;
        }

        String token = httpServletRequest.getHeader("token");

        if (!StringUtils.hasLength(token)) {
            log.info("请求头token为空，返回未登录的信息");
            Result notLogin = Result.error("NOT_LOGIN");
            String msg = JSONObject.toJSONString(notLogin);
            httpServletResponse.getWriter().write(msg);
            return;
        }

        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("令牌解析失败,返回未登录错误信息");
            Result notLogin = Result.error("NOT_LOGIN");
            String msg = JSONObject.toJSONString(notLogin);
            httpServletResponse.getWriter().write(msg);
            return;
        }

        log.info("令牌合法，放行");
        chain.doFilter(request,response);


    }

    @Override
    public void destroy() {
        log.info("destroy 方法");
    }
}
