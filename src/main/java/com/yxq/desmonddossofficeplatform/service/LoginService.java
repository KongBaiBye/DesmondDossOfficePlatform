package com.yxq.desmonddossofficeplatform.service;

import com.yxq.desmonddossofficeplatform.utils.ResultData;
import javax.servlet.http.HttpSession;

/**
 * @Author: yxq
 * @Date: 2023/01/14 20:27
 */
public interface LoginService {
    ResultData selectPassword(String username, String password, String captcha, HttpSession session);

    /**
     * @param username
     * @return
     */
    ResultData loginOut(String username);
}
