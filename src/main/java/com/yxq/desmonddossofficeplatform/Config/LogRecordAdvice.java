package com.yxq.desmonddossofficeplatform.Config;

import com.yxq.desmonddossofficeplatform.dao.DoctorDao;
import com.yxq.desmonddossofficeplatform.dao.LoginDao;
import com.yxq.desmonddossofficeplatform.dao.ManagerDao;
import com.yxq.desmonddossofficeplatform.dao.UserDao;
import com.yxq.desmonddossofficeplatform.utils.ResultData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author yxq
 * @Date 2023/3/21 17:22
 */
@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class LogRecordAdvice {
    private final ManagerDao managerDao;
    private final UserDao userDao;
    private final DoctorDao doctorDao;
    private final LoginDao loginDao;
    /**
     * 方法名字是以什么开头
     */
    private final String INSERT_USER = "insertUser";
    private final String UPDATE_USER = "updateUser";
    private final String DELETE_USER = "deleteUser";
    private final String INSERT_DOCTOR = "insertDoctor";
    private final String UPDATE_DOCTOR = "updateDoctor";
    private final String DELETE_DOCTOR = "deleteDoctor";
    private final String INSERT_DRUG = "insertDrug";
    private final String UPDATE_DRUG = "updateDrug";
    private final String DELETE_DRUG = "deleteDrug";

    /**
     * 定义切入点
     */
    @Pointcut("@annotation(com.yxq.desmonddossofficeplatform.annotation.LogRecord)")
    public void pointcut() {
    }


    /**
     * 定义环绕增强
     *
     * @param jp
     * @return {@link Object}
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        //从切入点获取方法签名
        MethodSignature signature = (MethodSignature) jp.getSignature();
        //获取方法对象
        Method method = signature.getMethod();
        //获取方法名字
        String methodName = method.getName();
        if (methodName.startsWith("delete")){
            //从切入点获取方法参数
            Object[] args = jp.getArgs();
            //被删除操作人ID
            String sid = (String) args[0];
            int id = Integer.parseInt(sid);
            HttpSession session = (HttpSession) args[1];
            //操作人
            String username = (String) session.getAttribute("username");
            HashMap<String, Object> handle = new HashMap<>();
            handle.put("operationCrew", username);
            //开始判断删除人的身份
            if (methodName.startsWith(DELETE_USER)) {
                Map<String, Object> map = managerDao.selectUserName(id);
                handle.put("logName",username +"删除用户"+map.get("name")+"信息");
                //删除用户执行这个
            } else if (methodName.startsWith(DELETE_DOCTOR)) {
                //id转成支付穿
                //删除医生执行这个
                String ssid = String.valueOf(id);
                Map<String, Object> map = loginDao.selectDoctorName(ssid);
                handle.put("logName",username +"删除医生"+map.get("name")+"信息");
            } else if (methodName.startsWith(DELETE_DRUG)) {
                Map<String, Object> drug = managerDao.selectDrugById(id);
                //删除药品执行这个
                handle.put("logName", username + "删除药品" + drug.get("drugName"));
            }
            //添加数据到数据库
            //后置增强，判断是否删除成功
            ResultData proceed = (ResultData) jp.proceed();
            Integer code = proceed.getCode();
            if (code == 0) {
                handle.put("success", 1);
            } else {
                handle.put("success", 0);
            }
            //往数据库中添加数据
            if (methodName.startsWith(DELETE_USER) || methodName.startsWith(DELETE_DOCTOR)) {
                //删除用户和医生执行这个
                loginDao.insertOperationLog(handle);
            } else if (methodName.startsWith(DELETE_DRUG)) {
                loginDao.insertDrugLog(handle);
            }
            return proceed;
        }else if (methodName.startsWith("update") || methodName.startsWith("insert")){
            //下面是增加删除药品操作
            //从切入点获取方法参数
            Object[] args = jp.getArgs();
            HttpServletRequest req = (HttpServletRequest) args[0];
            HttpSession session = (HttpSession) args[1];
            //操作人
            String username = (String) session.getAttribute("username");
            HashMap<String, Object> handle = new HashMap<>();
            handle.put("operationCrew", username);
            Map<String, String[]> map = req.getParameterMap();
            //开始判断删除人的身份
            String drugName = null;
            String uName = null;
            if (methodName.startsWith(INSERT_DRUG)||methodName.startsWith(UPDATE_DRUG)) {
                drugName = map.get("drugName")[0];
            } else {
                //被操作人的名字
                uName = map.get("name")[0];
            }
            if (methodName.startsWith("insertDrug")) {
                //添加药品
                handle.put("logName", "添加" + drugName + "");

            } else if (methodName.startsWith("updateDrug")){
                //修改药品
                handle.put("logName", "修改" + drugName + "");
            } else if (methodName.startsWith("insert")) {
                handle.put("logName", "添加" + uName + "");
            } else if (methodName.startsWith("update")) {
                handle.put("logName", "修改" + uName + "");
            }
            ResultData res = (ResultData) jp.proceed();
            Integer code = res.getCode();
            if (code == 0) {
                handle.put("success", 1);
            } else {
                handle.put("success", 0);
            }
            if (methodName.startsWith("insertDrug")||methodName.startsWith("updateDrug")) {
                loginDao.insertDrugLog(handle);
            } else {
                loginDao.insertOperationLog(handle);
            }
            return res;
        }
        return jp.proceed();
    }
}
