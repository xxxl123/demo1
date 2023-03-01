package com.rainng.coursesystem.SCN.course.utils;

import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j   // lombok中日志注解
@Aspect  // 表明是一个切面类
@Component
public class Weblog {
    public Weblog() {
    }

    /**
     * 定义切入点表达式
     * 访问修饰符 返回值 包名.包名.包名...类名.方法名(参数列表)
     * 权限修饰符可以使用默认 第一个*表示返回值类型  ..表示当前包以及其子包下 第二个*表示任意方法 (..)表示任意参数列表
     */
    private final String POINTCUT = "execution(* com.example.springboot.Controller..*(..))";

    /**
     * 前置通知，方法之前执行
     * @param joinPoint
     */
    @Before(POINTCUT)
    public void doBefore(JoinPoint joinPoint) {
        // 获取当前的HttpServletRequest对象
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取请求头中的User-Agent
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        // 打印请求的内容
        log.info("请求Url : {}", request.getRequestURL().toString());
        log.info("请求方式 : {}", request.getMethod());
        log.info("请求内容类型 : {}", request.getContentType());
        log.info("请求参数 : {}", Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 后置通知
     * @param joinPoint
     */
    @After(POINTCUT)
    public void doAfter(JoinPoint joinPoint) {
        System.out.println("方法名："+joinPoint.getSignature().getName()+"，方法执行完毕");
    }

    /**
     * 返回通知 正常结束时进入此方法
     *
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = POINTCUT)
    public void doAfterReturning(Object ret) {
        // 处理完请求，返回内容
        log.info("请求返回 : {}", ret);
    }

    /**
     * 异常通知： 1. 在目标方法非正常结束，发生异常或者抛出异常时执行
     *
     * @param throwable
     */
    @AfterThrowing(pointcut = POINTCUT, throwing = "throwable")
    public void doAfterThrowing(Throwable throwable) {
        // 保存异常日志记录
        log.error("发生异常时间 : {}", LocalDateTime.now());
        log.error("抛出异常 : {}", throwable.getMessage());
    }
}