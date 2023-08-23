package com.itheima.controller;

import com.itheima.pojo.Result;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jsc
 * @version 1.0
 */
@Slf4j
@RestController
public class SessionController {

    @GetMapping("/c1")
    public Result cookie01(HttpServletResponse httpServletResponse) {
        httpServletResponse.addCookie(new Cookie("login_name","itheima"));
        return Result.success();
    }

    @GetMapping("/c2")
    public Result cookie02(HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login_name")) {
                System.out.println("login_name: " + cookie.getValue());
            }
        }
        return Result.success();
    }

    @GetMapping("/s1")
    public Result session01(HttpSession httpSession) {
        log.info("HttpSession-01: {}",httpSession.hashCode());
        httpSession.setAttribute("loginUser","tom");
        return Result.success();
    }

    @GetMapping("/s2")
    public Result session02(HttpSession httpSession) {
        log.info("HttpSession-02: {}",httpSession.hashCode());
        Object loginUser = httpSession.getAttribute("loginUser");
        log.info("HttpSession-02 loginUser session: {}",loginUser);
        return Result.success();
    }
}
