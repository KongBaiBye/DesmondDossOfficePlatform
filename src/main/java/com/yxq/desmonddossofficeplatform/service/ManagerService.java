package com.yxq.desmonddossofficeplatform.service;


import com.yxq.desmonddossofficeplatform.utils.ResultData;

import java.util.Map;


/**
 * @Author: yxq
 * @Date: 2023/01/16 10:38
 */
public interface ManagerService {
    /**
     * 查询所有的用户信息
     */
    ResultData selectAllUserInfo(String page, String limit);

    /**
     * 查询所有的医生信息
     */
    ResultData selectAllDoctorInfo(String page, String limit);

    /**
     * 查询所有的科室医生信息
     */
    ResultData selectAllDepartmentDoctor(String page, String limit);

    /**
     * 查询全部的药品信息
     */
    ResultData selectAllDrugInfo(String page, String limit);

    /**
     * 查询全部的登录日志
     */
    ResultData selectLoginLog(String page, String limit);

    /**
     * 查询全部的药品日志
     *
     * @param page
     * @param limit
     */
    ResultData selectDrugLog(String page, String limit);

    /**
     * 搜索医生个人信息
     *
     * @param name
     * @return
     */
    ResultData selectDoctorPersonInfo(String name);

    /**
     * 添加医生
     *
     * @param map
     * @return
     */
    ResultData insertDoctorInfo(Map<String, String[]> map);

    /**
     * 修改医生信息
     *
     * @param map
     * @return
     */
    ResultData updateDoctorInfo(Map<String, String[]> map);

    /**
     * 根据工号删除医生信息
     *
     * @param empno
     * @return
     */
    ResultData removeDoctorInfo(String empno);

    /**
     * 重置医生密码
     *
     * @param empno
     * @return
     */
    ResultData updatePassword(String empno);

    /**
     * 搜索用户信息
     *
     * @param name
     * @return
     */
    ResultData selectUserInfo(String name);

    /**
     * 添加用户信息
     *
     * @param map
     * @return
     */
    ResultData insertUserInfo(Map<String, String[]> map);

    /**
     * 更新用户信息
     *
     * @param map
     * @return
     */
    ResultData updateUserInfo(Map<String, String[]> map);

    /**
     * 删除用户信息
     *
     * @param id
     * @return
     */
    ResultData removeUserInfo(String id);

    /**
     * 根据身份证号重置用户密码
     *
     * @param idCard
     * @return
     */
    ResultData updateUserPassword(String idCard);

    /**
     * 移出科室功能，根据工号把科室department置位未分配
     *
     * @param empno
     * @return
     */
    ResultData updateDoctorDepartmentOut(String empno);

    /**
     * 移入科室功能
     *
     * @param empno
     * @param department
     * @return
     */
    ResultData updateDoctorDepartmentOn(String empno, String department);

    /**
     * 根据药品名查询药品信息
     *
     * @param drugName
     * @return
     */
    ResultData selectDrugInfo(String drugName);

    /**
     * 根据症状模糊查询
     *
     * @param symptom
     * @return
     */
    ResultData selectSymptom(String symptom);

    /**
     * 添加药品信息
     * @param map
     * @return
     */
    ResultData insertDrug(Map<String,String[]> map);

    /**
     * 删除药品信息
     * @param id
     * @return
     */
    ResultData removeDrug(String id);

    /**
     *
     * @param map
     * @return
     */
    ResultData updateDrugInfo(Map<String,String[]> map);

    /**
     * 查询操作日志
     * @return
     */
    ResultData selectAllOperationLog();
}
