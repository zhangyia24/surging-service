package com.surging.entity;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Created by zhangdongmao on 2019/1/15.
 */
//@Component
//@Aspect
public class WebLogAspect {
    @Pointcut("execution(public * com.surging.controller..*.*(..))")
    public void weblog() {}

    @Before("weblog()")
    public void webBefore() {
        System.out.println("在更新之前");
        System.out.println("stop");
        return;
    }

    @After("weblog()")
    public void webAfter() {
        System.out.println("在更新之后");
    }

    @Around("weblog()")
    public void webAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕通知前……");
        pjp.proceed();
        System.out.println("环绕通知后……");
    }

    @AfterReturning("weblog()")
    public void webAfterReturn() {
        System.out.println("afterReturning ……");
    }
}
