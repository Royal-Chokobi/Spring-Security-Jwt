package com.jyl.spring.auth.common.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * packageName    : com.jyl.spring.auth.common.logging
 * fileName       : LogAspect
 * author         : leejaeyoon
 * date           : 2022/05/18
 * description    : AOP - log
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/18        leejaeyoon       최초 생성
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    @After("execution(protected * com.jyl.spring.auth.common.exception..*(..) ) && args(ex)")
    public void errorsThrowsAspect(JoinPoint joinPoint, Exception ex){
        var methodName = joinPoint.getSignature().getName();
        log.error("AOP Logging >>> Method Name : "+methodName+" | Errors Message : "+ex);
    }

    @Around("execution(* com.jyl.spring.auth.restful.*.*Controller.*(..))")
    public Object calcPerformanceAdvice(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start();
        Object result = pjp.proceed();
        var functionName = pjp.getSignature().getName();
        sw.stop();

        log.info("Function : {} | Run Time: {} ms", functionName, sw.getLastTaskTimeMillis());
        return result;
    }

}
