package com.demo.backend.bean;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.demo.backend.bean.Docker.say(..))")
    public void beforelogger(JoinPoint jp) {
        // System.out.println("jp: " + jp.getSignature());
        System.out.println("jp: " + jp.getArgs()[0].toString());
        System.out.println("Before Logger!");
    }

    // any return, any package, any class, method say
    // execution(ret-type-pattern name-pattern(param-pattern))
    // ret-type-pattern matches the return type, * for any

    // name-pattern matches the method nam e, you can use * as wildcard and .. to
    // indicate sub-package

    // param-pattern matches the method parameters, (..) for any number of
    // parameters

    // @After("execution(* com.demo..*.*(..))")
    @After("execution(* *..*.say(..))")
    public void Afterlogger() {
        System.out.println("After Logger!");
    }

    // TODO fix not work
    @Pointcut("execution(* *..*.retrivePill())")
    public void afterReturningPointCut() {
        System.out.println("hey!");
    }

    // TODO fix not work
    @AfterReturning(pointcut = "afterReturningPointCut()", returning = "retVal")
    public void afterReturning(String retVal) {
        System.out.println("AfterReturning : " + retVal);
    }

}
