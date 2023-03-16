package com.yxq.desmonddossofficeplatform.service.impl;
import cn.hutool.extra.servlet.ServletUtil;
import com.yxq.desmonddossofficeplatform.dao.LoginDao;
import com.yxq.desmonddossofficeplatform.service.LoginService;
import com.yxq.desmonddossofficeplatform.utils.RedisUtils;
import com.yxq.desmonddossofficeplatform.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author: yxq
 * @Date: 2023/01/14 20:28
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    LoginDao loginDao;
    @Override
    public ResultData selectPassword(String username, String password, String captcha, HttpSession session) {
        Map<String, Object> map = loginDao.selectPassword(username);
        session.setAttribute("roleName",map.get("roleName"));

            if (password.equals(map.get("password"))){
                return ResultData.success(map.get("roleName"));
            }else {
                return ResultData.fail();
            }
    }

    @Override
    public ResultData loginOut(String username) {
       return ResultData.success();
    }
}
