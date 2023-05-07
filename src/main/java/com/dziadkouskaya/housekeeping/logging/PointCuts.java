package com.dziadkouskaya.housekeeping.logging;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PointCuts {

    @Pointcut("execution(public * getAll(..)) || execution(public * getAll*(..))")
    public void getAll() {
    }

    @Pointcut("execution(public * com.dziadkouskaya.housekeeping.facade.*.getAll(..)) " +
        "|| execution(public * com.dziadkouskaya.housekeeping.facade.*.getAll*(..))")
    public void getAllInFacades() {
    }

    @Pointcut("getAll()  && !getAllInFacades()")
    public void gettingAllEntities() {
    }

}
