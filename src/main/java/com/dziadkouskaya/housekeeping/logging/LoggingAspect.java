package com.dziadkouskaya.housekeeping.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.data.domain.Page;
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
        log.info("Number of entities is {}", List.of(result).size());
    }

    @AfterReturning(pointcut = "com.dziadkouskaya.housekeeping.logging.PointCuts.getByParametersWithoutId()",
        returning = "result")
    public void aroundGetEntitiesByParameters(JoinPoint joinPoint, Page result) {
        log.info("Number of returning values for {} is {}.", joinPoint.getArgs(), result.getTotalElements());
    }
}
