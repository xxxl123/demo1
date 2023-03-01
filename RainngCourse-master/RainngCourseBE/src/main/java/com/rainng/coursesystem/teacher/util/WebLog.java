package com.rainng.coursesystem.teacher.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Aspect
public class WebLog {
    //请求访问日志

    //1.创建日志管理工厂
    private final Logger logger =LoggerFactory.getLogger(WebLog.class);

    //2.设置切点   在何时何地执行我们的日志管理
    //第一个*指的是方法的返回类型  controller包下的所有的类   所有的方法 方法中的所有的参数
    @Pointcut("execution(* com.rainng.coursesystem.teacher.controller.*.*(..))")
    public void WebLog(){
        //切点的执行方法
    }

    //before after around returning....
    //3.设置具体的记录内容
    @Before("WebLog()")
    public void doBefore(JoinPoint joinPoint){
        //记录参数，记录访问时间，记录访问请求的接口名称，方法名称....
        //在执行请求接口前需要记录的内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("================开始记录日志=======================");

        //1.记录请求的ip地址
        logger.info("IP addr   :{}",request.getRemoteAddr());
        //2.记录请求的url
        logger.info("URL    :{}",request.getRequestURL().toString());
        //3.记录请求的方法
        logger.info("HTTP  method    :{}",request.getMethod());
        //4.记录请求的controller全路径和执行的方法名称
        logger.info("class   method   :{}",joinPoint.getSignature().getDeclaringType(),joinPoint.getSignature().getName());
        //5.记录请求中的参数
        //获取参数
        Object[] args = joinPoint.getArgs();
        //过滤掉参数中的request和response
        Stream<?> stream = ArrayUtils.isEmpty(args) ? Stream.empty(): Arrays.asList(args).stream();
        List<Object> logArgs = stream.filter(arg->!(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletRequest))
                .collect(Collectors.toList());
        //将过滤后的参数集合  转化成json格式的数据
        String str = JSON.toJSONString(logArgs);
        logger.info("Request  args  :{}",str);
    }

    @After("WebLog()")
    public void doAfter(){
        //记录返回请求的内容
        logger.info("=====================请求结束=============================");
    }

    @Around("WebLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //1.记录执行时间，和返回的内容
        Long startTime = System.currentTimeMillis();
        //回调执行controller的方法  并获取返回值
        Object result = proceedingJoinPoint.proceed();
        //2.记录方法的返回值  转化成json数据
        logger.info("Response   args   :{}",new JSONObject().toJSONString(result));
        //3.记录一下执行方法一共花了多少时间
        logger.info("Time-Consuming   :{}",System.currentTimeMillis()-startTime);
        return result;
    }
}
