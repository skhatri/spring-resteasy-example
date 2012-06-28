package com.opencode.ps.services;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class FundReaderAspect {

    @Pointcut("execution(* com.opencode.ps.services.FundReader.*(..))")
    void fundReaderPointcut() {
    }

    @Around(value = "com.opencode.ps.services.FundReaderAspect.fundReaderPointcut()")
    public Object fundReadAdvice(ProceedingJoinPoint jp) throws Throwable {
        Object val = null;
        long startTime = System.currentTimeMillis();
        try {
            val = jp.proceed();
        } catch (Throwable e) {
            throw e;
        } finally {
            long endTime = System.currentTimeMillis();
            System.out.printf("method %s execution took %d ms\n", jp.getSignature().getName(), (endTime - startTime));
        }
        return val;
    }

}
