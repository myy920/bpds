package com.myy.bpds.common.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 请求日志切面 用于记录所有 Controller 层的请求和响应信息
 */
@Slf4j
@Aspect
@Component
public class RequestAspect {
    /**
     * 定义切点：拦截所有标注了 @RestController 或 @Controller 的类中的方法
     */
    @Pointcut(
            "@within(org.springframework.web.bind.annotation.RestController) || @within(org.springframework.stereotype.Controller)")
    public void controllerPointcut() {
    }

    /**
     * 环绕通知：记录方法执行时间和结果
     */
    @Around("controllerPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取请求上下文
        String requestMethod = "unknow";
        String requestUrl = "unknow";
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            requestMethod = request.getMethod();
            requestUrl = request.getRequestURL().toString();
        }
        // 获取方法信息
        String simpleClassName = joinPoint.getSignature().getDeclaringType().getSimpleName(); // 简单类名
        String methodName = joinPoint.getSignature().getName(); // 方法名
        log.info("{} {}  {}.{} ==>", requestMethod, requestUrl, simpleClassName, methodName);
        long startTime = System.currentTimeMillis();
        // 执行目标方法
        Object result = joinPoint.proceed();
        // 计算执行时间
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        log.info("{} {}  {}.{} <== cost {} ms", requestMethod, requestUrl, simpleClassName, methodName, duration);

        return result;
    }
}
