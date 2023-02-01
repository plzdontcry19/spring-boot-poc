package com.demo.backend.bean;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthenAspect {

    // @Pointcut("within(com.demo..*.*)")
    // public void authenPointCut() {

    // }

    // @Before("authenPointCut()")
    // public void authenticate() {
    //     System.out.println("Authenticating the request");
    // }
}