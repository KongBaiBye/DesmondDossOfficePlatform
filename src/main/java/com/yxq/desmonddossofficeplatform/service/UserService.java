package com.yxq.desmonddossofficeplatform.service;
import com.yxq.desmonddossofficeplatform.utils.ResultData;

import java.util.Map;

/**
 * @Author: yxq
 * @Date: 2023/01/20 16:05
 */
public interface  UserService {
    /**
     * 查询自己的健康信息
     * @param username
     * @return
     */
    ResultData selectUserPersonHealthInfo(String username);

    /**
     * 查询个人就诊历史
     * @param username
     * @return
     */
    ResultData selectUserPersonHealthHistory(String username);

    /**
     * 查询家人健康信息
     * @param name
     * @param idCard
     * @return
     */
    ResultData selectFamilyHealthInfo(String name,String idCard);

    /**
     * 查询家人就诊信息
     * @param name
     * @param idCard
     * @return
     */
    ResultData selectFamilyHealthHistory(String name,String idCard);

    /**
     * 注册账号
     * @param map
     * @return
     */
    ResultData insertUserLogin(Map<String,String[]> map);
}
