package com.hunglp.springaop.aspect;


import com.hunglp.springaop.dao.AccountDAO;
import com.hunglp.springaop.model.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;

@Aspect
@Component
public class LoggingAspect {

    //--------------------------------------------------BEFORE----------------------------------------------------------

    @Before("execution(public void add*())")            // Match any method start with "add"
    //@Before("execution(* add*())")                    // Add any method start with "add" and any return type
    // Parameter Pattern Wildcards:
    // () : matches a method with no arguments
    // (*) : matches a method with one argument of any type
    // (..) : matches a method with 0 or more argument of any type

    public void beforeAddAccount() {
        System.out.println("Executing @Before advice on addAccount()");


    }


    // Point cut for all method in package DAO
    @Pointcut("execution(* com.hunglp.springaop.dao.*.*(..))")
    public void forDaoPackage() {
        System.out.println("Execution @Pointcut for all method in DAO package, This will call performAPI first ");
    }

    // Pointcut for getter method
    @Pointcut("execution(* com.hunglp.springaop.dao.*.get*(..))")
    public void forGetterMethod() {

    }

    // Pointcut for setter method
    @Pointcut("execution(* com.hunglp.springaop.dao.*.set*(..))")
    public void forSetterMethod() {

    }

    // Pointcut for all method in package.. exclude getter/setter method
    @Pointcut("forDaoPackage() && !(forGetterMethod() || forSetterMethod())")
    private void forDaoPackageNoGetterSetter() {

    }

    @Before("forDaoPackage()")
    public void performAPI(JoinPoint joinPoint) {
        System.out.println("Performing API");
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        Object[] args = joinPoint.getArgs();
        System.out.println("Method argument : ");
        for (Object arg : args) {
            System.out.println(arg);
        }


        System.out.println("Method signature : " + joinPoint);
    }


    // ----------------------------------------------------AFTER--------------------------------------------------------

    @AfterReturning(pointcut = "execution(* com.hunglp.springaop.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccounts(JoinPoint joinPoint, List<Account> result) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("=> Executing @AfterReturning on method  : " + methodSignature.toShortString());

        // Convert the accounts to upperCase
        result.forEach(a -> a.setName(a.getName().toUpperCase(Locale.ROOT)));

        System.out.println(" => Result : " + result);

    }

    // ---------------------------------------------AFTER THROWING------------------------------------------------------
    // ----USE CASE :
    // 1. Log the Exception
    // 2. Perform auditing on the exception
    // 3. Notify devops team via email or SMS

    @AfterThrowing(pointcut = "execution(* com.hunglp.springaop.dao.AccountDAO.findAccounts(..))",
                   throwing = "exception")
    public void afterThrowingFindAccount(JoinPoint joinPoint, Throwable exception) {

        System.out.println("==>> Executing @AfterThrowing, Exception : " + exception);
    }

    // ----------------------------------------------AFTER ADVICE-------------------------------------------------------
    // USE CASE:
    // 1. Log the Exception / or perform auditing
    // 2. Code to run regardless of method outcome
    // 3. Encapsulate this functionality in AOP aspect for easy reuse
    // Tips : After advice does not have access to the exception,If you need exception, then use @AfterThrowingAdvice


    @After("execution(* com.hunglp.springaop.dao.AccountDAO.findAccounts(..))")
    public void afterAdviceFindAccount(JoinPoint joinPoint){
        System.out.println(" =>> Executing @After Advice (Similar Finally) ");

    }






}
