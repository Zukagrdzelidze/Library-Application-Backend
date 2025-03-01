package com.example.finalproject.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Aspect
@Slf4j
@Component
public class LoggingAspect {
    @Pointcut("execution(* com.example.finalproject..*(..))")
    public void articleListPointcut(){ }

    @Around("articleListPointcut()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {

        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();

        log.info("Method '{}' called with arguments:", methodName);
        for (int i = 0; i < args.length; i++) {
            log.info("Arg {}: {}", i, args[i]);
        }
        Object result = pjp.proceed();
        return result;
    }



    @Before("execution(* com.example.finalproject..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        log.info(className + "'s method " + methodName + " is called");
    }

    @After("execution(* com.example.finalproject..*(..))")
    public void logAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        log.info(className + "'s method " + methodName + " execution finished");
    }

}
