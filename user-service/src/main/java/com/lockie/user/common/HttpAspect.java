package com.lockie.user.common;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 邹细良
 * @Date: 2019/3/29 11:28
 * @Description: 定义一个切面记录日志
 */
@Aspect
@Component
public class HttpAspect {
    private static final Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    /**
     * 定义一个公共的记录日志的方法
     * 拦截 UserController 下面的所有方法
     * 拦截 UserController 下面 userList 方法里的任何参数 (..) 表示拦截任何参数
     * 写法：@Before("execution(public * com.angelo.controller.UserController.userList(..))")
     */
    @Pointcut("execution(public * com.lockie.user.controller.UserController.*(..))")
    public void log() {
    }

    /**
     * 前置通知，目标方法调用前被调用
     * @param joinPoint
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        Map params = new HashMap();
        // 获取请求的url
        params.put("url", request.getRequestURL());
        // 获取请求方式
        params.put("method", request.getMethod());
        // 获取请求的IP地址
        params.put("ip", request.getRemoteAddr());
        // 获取类名
        params.put("className", joinPoint.getSignature().getDeclaringTypeName());
        // 获取类方法
        params.put("classMethod", joinPoint.getSignature().getName());
        // 请求参数
        params.put("args", joinPoint.getArgs());

        // 转化成json输出
        logger.info("切面before执行了 >>>");
        logger.info(JSONObject.toJSONString(params));
    }

    /**
     * 后置最终通知，目标方法执行完执行
     * 不管是抛出异常或者正常退出都会执行
     */
    @After("log()")
    public void doAfter() {
        logger.info("切面after执行了 >>>");
    }

    /**
     * 环绕通知
     * 环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     * 环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     * @param joinPoint
     */
    @Around("log()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        try {
            Object obj = joinPoint.proceed();
            logger.info("切面Around，接口返回结果：" + JSONObject.toJSON(obj));
            return obj;
        } catch (Throwable e) {
            e.printStackTrace();
            logger.info("切面Around执行了，error异常");
            return null;
        }
    }

    /**
     * 后置返回通知
     * 如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息
     * 如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     * 只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行
     * @param object
     */
    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        logger.info("切入点后返回通知: " + JSONObject.toJSON(object));
    }

    /**
     * 后置异常通知
     */
    @AfterThrowing(pointcut = "log()")
    public void doAfterThrow() {
        logger.info("异常切面执行了 >>>");
    }
}
