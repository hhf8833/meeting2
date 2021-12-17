package com.hhf.spring5.aopanno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author HP
 * 被增强类
 * @Order(1)多个增强类都对同一个方法增强，设置优先级值越小，优先级越高
 */
@Order(1)
@Component
@Aspect
public class UserProxy {
//前置通知
    @Before(value = "execution(* com.hhf.spring5.aopanno.User.add(..))")
    public  void  before(){
        System.out.println("before.....");
    }
//后置通知
    @AfterReturning(value ="execution(* com.hhf.spring5.aopanno.User.add(..))")
    public void afterReturning() {
        System.out.println("afterReturning.........");
    }
//最终通知
    @After(value ="execution(* com.hhf.spring5.aopanno.User.add(..))")
    public void after() {
        System.out.println("after.........");
    }
//异常通知
    @AfterThrowing(value = "execution(* com.hhf.spring5.aopanno.User.add(..))")
    public void afterThrowing() {
        System.out.println("afterThrowing.........");
    }
//环绕通知
    @Around(value = "execution(* com.hhf.spring5.aopanno.User.add(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws
            Throwable {
        System.out.println("环绕之前.........");
//被增强的方法执行
        proceedingJoinPoint.proceed();
        System.out.println("环绕之后.........");
    }
}
