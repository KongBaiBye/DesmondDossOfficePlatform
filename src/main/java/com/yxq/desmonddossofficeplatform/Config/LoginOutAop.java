package com.yxq.desmonddossofficeplatform.Config;

import com.yxq.desmonddossofficeplatform.dao.LoginDao;
import com.yxq.desmonddossofficeplatform.utils.ResultData;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @Author: yxq
 * @Date: 2023/01/16 19:53
 *  AOP登录操作后置增强，记录登录日志信息
 */
@Aspect
@Component
public class LoginOutAop {
    @Resource
    private LoginDao loginDao;
    /**
     * 定义切入点
     */
    @Pointcut("execution(* com.yxq.desmonddossofficeplatform.service.impl.LoginServiceImpl.loginOut(..))")
    private void myPointcut(){}

    @Around("myPointcut()")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        //从切入点获取方法参数
        Object[] args = jp.getArgs();
        String username = (String) args[0];
        loginDao.insertLoginOutLog(username);
    }
}

