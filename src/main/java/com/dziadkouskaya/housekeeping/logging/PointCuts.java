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

    @Pointcut("execution(public * com.dziadkouskaya.housekeeping.facade.*.get*(..)) " +
        "|| execution(public * com.dziadkouskaya.housekeeping.facade.*.get*(..))")
    public void getInFacades() {
    }

    @Pointcut("execution(public * com.dziadkouskaya.housekeeping.controller.*.get*(..)) " +
        "|| execution(public * com.dziadkouskaya.housekeeping.controller.*.get*(..))")
    public void getInControllers() {
    }

    @Pointcut("getAll()  && !getInFacades() && !getInControllers()")
    public void gettingAllEntities() {
    }

    @Pointcut("execution(public * getById(..))")
    public void getById(){}

    @Pointcut("execution(public * getBy*(..))")
    public void getByAnyParameter(){}

    @Pointcut("getByAnyParameter() && !getById() && !getInFacades() && !getInControllers()")
    public void getByParametersWithoutId() {}

}
