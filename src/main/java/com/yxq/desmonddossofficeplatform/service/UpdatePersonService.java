package com.yxq.desmonddossofficeplatform.service;
import com.yxq.desmonddossofficeplatform.utils.ResultData;

import java.util.Map;

/**
 * @Author: yxq
 * @Date: 2023/01/17 17:54
 */
public interface UpdatePersonService {
    /**
     * 管理员和医生修改个人信息
     * @param map
     * @return
     */
    ResultData updatePersonInfo(Map<String,String[]> map);

    /**
     * 管理员和医生根据用户名查询个人信息
     * @param username
     * @return
     */
    ResultData selectPersonInfo(String username);
    /**
     * 用户修改个人信息
     * @param map
     * @return
     */
    ResultData userUpdatePersonInfo(Map<String,String[]> map);

    /**
     * 用户根据用户名查询个人信息
     * @param username
     * @return
     */
    ResultData userSelectPersonInfo(String username);

    /**
     * 修改个人密码
     * @param username
     * @param oldPassword
     * @param newPassword
     * @return
     */
    ResultData changePassword(String username ,String oldPassword,String newPassword);
}
