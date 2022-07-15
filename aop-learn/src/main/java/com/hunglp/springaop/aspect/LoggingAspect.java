package com.hunglp.springaop.aspect;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(public void add*())")            // Match any method start with "add"
    //@Before("execution(* add*())")                    // Add any method start with "add" and any return type
    // Parameter Pattern Wildcards:
    // () : matches a method with no arguments
    // (*) : matches a method with one argument of any type
    // (..) : matches a method with 0 or more argument of any type

    public void beforeAddAccount(){
        System.out.println("Executing @Before advice on addAccount()");
    }

}
