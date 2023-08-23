package com.itheima.interceptor;

import com.alibaba.fastjson2.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jsc
 * @version 1.0
 */
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {


    /**
     * 资源目标执行之前
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler chosen handler to execute, for type and/or instance evaluation
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle...");

//        String url = request.getRequestURL().toString();
//        log.info("请求的url：{}",url);
//
//        if (url.contains("login")) {
//            log.info("登录请求 放行：{}",url);
//            return true;
//        }

        String token = request.getHeader("token");

        if (!StringUtils.hasLength(token)) {
            log.info("请求头token为空，返回未登录的信息");
            Result notLogin = Result.error("NOT_LOGIN");
            String msg = JSONObject.toJSONString(notLogin);
            response.getWriter().write(msg);
            return false;
        }

        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("令牌解析失败,返回未登录错误信息");
            Result notLogin = Result.error("NOT_LOGIN");
            String msg = JSONObject.toJSONString(notLogin);
            response.getWriter().write(msg);
            return false;
        }

        log.info("令牌合法，放行");
        return true;
    }

    /**
     * 资源目标执行之后
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler the handler (or {@link HandlerMethod}) that started asynchronous
     * execution, for type and/or instance examination
     * @param modelAndView the {@code ModelAndView} that the handler returned
     * (can also be {@code null})
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle...");
    }

    /**
     * 视图渲染完成后
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler the handler (or {@link HandlerMethod}) that started asynchronous
     * execution, for type and/or instance examination
     * @param ex any exception thrown on handler execution, if any; this does not
     * include exceptions that have been handled through an exception resolver
     * @throws Exception
     */

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion");
    }
}
