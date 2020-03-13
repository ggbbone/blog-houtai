package com.yzg.blog.portal.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yzg on 2020/3/12
 */
@org.aspectj.lang.annotation.Aspect
@Component
@Slf4j
public class Aspect {
    //统一切点,对com.kzj.kzj_rabbitmq.controller及其子包中所有的类的所有方法切面
    @Pointcut("execution(public * com.yzg.blog.portal.controller..*.*(..))")
    public void controller() {
    }


    //@Around：环绕通知
    @Around("controller()")
    public Object ControllerAround(ProceedingJoinPoint pjp) throws Throwable {
        Map<String,Object> data = new HashMap<>();
        //获取目标类名称
        String clazzName = pjp.getTarget().getClass().getName();
        //获取目标类方法名称
        String methodName = pjp.getSignature().getName();

        //记录类名称
        data.put("clazzName",clazzName);
        //记录对应方法名称
        data.put("methodName",methodName);
        //记录请求参数
        data.put("params", Arrays.toString(pjp.getArgs()));
        //开始调用时间
        // 计时并调用目标函数
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long time = System.currentTimeMillis() - start;

        //设置消耗总时间
        data.put("consumeTime",time + "ms");
        log.info(data.toString());
        return result;

    }


}
