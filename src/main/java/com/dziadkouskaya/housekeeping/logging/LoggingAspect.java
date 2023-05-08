package com.dziadkouskaya.housekeeping.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
@Slf4j
public class LoggingAspect {
    @Before("com.dziadkouskaya.housekeeping.logging.PointCuts.gettingAllEntities()")
    public void beforeGetEntities() {
        log.info("Get all entities.");
    }

    @AfterReturning(pointcut = "com.dziadkouskaya.housekeeping.logging.PointCuts.gettingAllEntities()",
        returning = "result")
    public void afterGetAllEntities(Object result) {
        log.info("Number of entities is {}", ((List)result).size());
    }
    @Around("com.dziadkouskaya.housekeeping.logging.PointCuts.getByParametersWithoutId()")
    public void aroundGetEntitiesByParameters(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Get entities by parameters: {}.", joinPoint.getArgs());
        joinPoint.proceed();
        log.info("Get entities: {}", joinPoint.getKind());

    }

}
