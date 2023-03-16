package com.yxq.desmonddossofficeplatform.service;

import com.yxq.desmonddossofficeplatform.utils.ResultData;

import java.util.Map;

/**
 * @Author: yxq
 * @Date: 2023/01/17 10:42
 */
public interface DoctorService {
    /**
     * 查询全部居民的健康信息
     *
     * @param page
     * @param limit
     * @return
     */
    ResultData selectAllUserHealthInfo(String page, String limit);

    /**
     * 查询全部居民的就诊历史
     *
     * @param page
     * @param limit
     * @return
     */
    ResultData selectAllUserHealthHistory(String page, String limit);

    /**
     * 查询用户健康信息
     *
     * @param name
     * @return
     */
    ResultData selectUserPersonInfo(String name);

    /**
     * 添加用户健康信息
     *
     * @param map
     * @return
     */
    ResultData insertUserHealthInfo(Map<String, String[]> map);

    /**
     * 删除用户健康信息
     *
     * @param id
     * @return
     */
    ResultData removeUserHealthInfo(String id);

    /**
     * 修改用户健康信息
     *
     * @param map
     * @return
     */
    ResultData updateUserHealthInfo(Map<String, String[]> map);

    /**
     * 查询居民健康历史
     *
     * @param name
     * @param onTime
     * @param outTime
     * @return
     */
    ResultData selectUserHealthHistory(String name, String idCard, String onTime, String outTime);

    /**
     * 查询医保卡信息
     *
     * @param idCard
     * @return
     */
    ResultData selectMedicareCard(String idCard);

    /**
     * 添加到病历单
     * @param map
     * @return
     */
    ResultData addDrugGoMedical(Map<String, String[]> map);

    /**
     * 查询病历单
     * @return
     */
    ResultData selectDrugMedical();

    /**
     * 删除病历单中的药品
     * @param id
     * @return
     */
    ResultData removeDrug(String id);

    /**
     * 病历单提交
     * @param map
     * @return
     */
    ResultData commit(Map<String,String[]> map);

    /**
     * 查询门诊日志
     * @return
     */
    ResultData selectOutpatientLog();


    /**
     * 该状态
     * @param map
     * @return
     */
    ResultData updatePay(Map<String,String[]> map);

}
