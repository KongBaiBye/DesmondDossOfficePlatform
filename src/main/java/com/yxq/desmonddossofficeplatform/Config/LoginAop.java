package com.yxq.desmonddossofficeplatform.Config;

import com.yxq.desmonddossofficeplatform.dao.LoginDao;
import com.yxq.desmonddossofficeplatform.utils.ResultData;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
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
public class LoginAop{
    @Resource
    private LoginDao loginDao;
    /**
     * 定义切入点
     */
    @Pointcut("execution(* com.yxq.desmonddossofficeplatform.service.impl.LoginServiceImpl.selectPassword(..))")
    private void myPointcut(){}

    @Around("myPointcut()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        //从切入点获取方法签名
        MethodSignature signature = (MethodSignature) jp.getSignature();
        //获取方法对象
        Method method = signature.getMethod();
        //从切入点获取方法参数
        Object[] args = jp.getArgs();
        Object returnValue = jp.proceed();
        HashMap<String, Object> map = new HashMap<>();
        Object username = args[0];
        ResultData ret= (ResultData) returnValue;
        Integer code = ret.getCode();
        Object data = ret.getData();
        map.put("username",username);
        if ("验证码错误".equals(data)){
            map.put("success",0);
            map.put("msg","验证码错误");
        } else if (code == -1){
            map.put("success",0);
            map.put("msg","密码错误");
        }else {
            map.put("success",1);
            map.put("msg","");
        }
        loginDao.insertLoginLog(map);
        return returnValue;
    }
}

