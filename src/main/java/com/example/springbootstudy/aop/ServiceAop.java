package com.example.springbootstudy.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
@Slf4j
public class ServiceAop {

    @Pointcut("@annotation(com.example.springbootstudy.aop.AopService)")
    public void pointcutxx() {

    }

    @Around("pointcutxx()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        log.info("方法执行之前");
        Object result = point.proceed();
        log.info("方法执行之后,返回值 " + result);
        return result;
    }

}