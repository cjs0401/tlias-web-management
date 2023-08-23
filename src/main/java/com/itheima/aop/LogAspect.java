package com.itheima.aop;

import com.alibaba.fastjson2.JSONObject;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author jsc
 * @version 1.0
 */

@Slf4j
@Component
@Aspect
public class LogAspect {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private OperateLogMapper operateLogMapper;

//    @Pointcut("execution(* com.itheima.service.DeptService.list())")
    @Pointcut("@annotation(com.itheima.aop.Log)")
    public void pt() {};


    @Around("pt()")
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("环绕前...");
        String jwt = httpServletRequest.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operatorId = (Integer)claims.get("id");
        LocalDateTime operateTime = LocalDateTime.now();
        String className = joinPoint.getTarget().getClass().getName();
        log.info("类名：{}",className);
        log.info("方法签名：{}",joinPoint.getSignature());
        String methodName = joinPoint.getSignature().getName();
        log.info("方法名：{}",methodName);
        String methodParams = Arrays.toString(joinPoint.getArgs());
        log.info("参数：{}", methodParams);
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        log.info(joinPoint.getSignature() + " 执行耗时: {} ms ",end - begin);
        String returnValue = JSONObject.toJSONString(result);
        OperateLog operateLog = new OperateLog(null,operatorId,operateTime,className,methodName,methodParams,returnValue,(end - begin));
        operateLogMapper.insert(operateLog);
        log.info("环绕后...");
        log.info("AOP 记录操作日志：{}",operateLog);
        return result;
    }
}
