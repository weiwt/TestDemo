package com.wwt.test.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author wwt
 * @date 2017/4/18 17:52
 */

@Aspect
@Component
public class TimeRecorder {

    private ThreadLocal<Long> timeRecorder = new ThreadLocal<Long>();

    @Pointcut("execution(* com.wwt.test.service.TestService.*(..)) || execution(* com.wwt.test.service.TestService.run())")
    public void pointCut(){}

    @Before("pointCut()")
    public void before(){
        timeRecorder.set(System.currentTimeMillis());
    }

    @After("pointCut()")
    public void after(JoinPoint joinPoint){
        long now = System.currentTimeMillis();
        Long before = timeRecorder.get();
        long consumeTime = now - before;
        String simpleName = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        System.err.println(simpleName + "." + methodName + " consume time:" + consumeTime + " ms");
    }
}
