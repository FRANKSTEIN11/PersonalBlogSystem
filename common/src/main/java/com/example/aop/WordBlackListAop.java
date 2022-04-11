package com.example.aop;


import com.example.conf.ResultJson;
import com.example.utils.WordBlackListUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@Order(0)
public class WordBlackListAop {

    //定义切点,注解为切入点
    @Pointcut("@annotation(com.example.annonation.CheckWord)")
    public void viewRecordsPointCut() {

    }

    /**
     * before 目标方法执行前执行，前置通知
     * after 目标方法执行后执行，后置通知
     * after returning 目标方法返回时执行 ，后置返回通知
     * after throwing 目标方法抛出异常时执行 异常通知
     * around 在目标函数执行中执行，可控制目标函数是否执行，环绕通知
     */

    @Before("viewRecordsPointCut()")
    public void before(JoinPoint joinPoint) throws Throwable {
        log.info("进入Before通知...");
    }

    @After("viewRecordsPointCut()")
    public void after(JoinPoint joinPoint) throws Throwable {
        log.info("进入After通知....");
    }

    @AfterReturning("viewRecordsPointCut()")
    public void afterReturning(JoinPoint joinPoint) throws Throwable {
        log.info("进入AfterReturning通知....");
    }

    @AfterThrowing("viewRecordsPointCut()")
    public void afterThrowing(JoinPoint joinPoint) throws Throwable {
        log.info("进入AfterThrowing通知....");
    }


    @Around("viewRecordsPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("进入controller==>Around通知....");
        /**
         * 进行业务操作
         */

//        //从cookies中获取userId,和前端传来的id进行对比
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        String cookieValue = CookieStoreBrowserHelper.getValue(request);
//        String userId = SessionAndCookieHelper.parseCookieValueToUserId(cookieValue);

        Object[] inputParameters = joinPoint.getArgs();        //入参
        for (Object inputParameter : inputParameters) {
            if (inputParameter != null) {
                if (WordBlackListUtil.isContains(inputParameter.toString())) {
                    log.info("存在敏感词!: " + inputParameter.toString());
                    return ResultJson.error("存在敏感词!");
                }
            }
        }
        return joinPoint.proceed();
    }

}
