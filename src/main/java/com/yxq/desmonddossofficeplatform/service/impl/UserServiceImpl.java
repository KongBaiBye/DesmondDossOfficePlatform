package com.yxq.desmonddossofficeplatform.service.impl;

import com.yxq.desmonddossofficeplatform.dao.UserDao;
import com.yxq.desmonddossofficeplatform.service.UserService;
import com.yxq.desmonddossofficeplatform.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yxq
 * @Date: 2023/01/20 16:05
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;
    /**
     * 私有化方法把map<string,string[]> 转化为map<string,obj>
     *
     * @param map map<string,string[]>
     * @return map<string, obj>
     */
    private Map<String, Object> conversion(Map<String, String[]> map) {
        HashMap<String, Object> hashMap = new HashMap<>(map.size());
        map.forEach((k, v) -> hashMap.put(k, v[0]));
        return hashMap;
    }
    @Override
    public ResultData selectUserPersonHealthInfo(String username) {
        return ResultData.success(userDao.selectUserPersonHealthInfo(username));
    }


    @Override
    public ResultData selectUserPersonHealthHistory(String username) {
        return ResultData.success(userDao.selectUserPersonHealthHistory(username));
    }

    @Override
    public ResultData selectFamilyHealthInfo(String name, String idCard) {
        return ResultData.success(userDao.selectFamilyHealthInfo(name,idCard));
    }

    @Override
    public ResultData selectFamilyHealthHistory(String name, String idCard) {
        return ResultData.success(userDao.selectFamilyHealthHistory(name,idCard));
    }

    @Override
    public ResultData insertUserLogin(Map<String, String[]> map) {
        Map<String, Object> hashMap = conversion(map);
        if ( hashMap.get("password").equals(hashMap.get("confirmPassword"))){
           if (1 == userDao.insertUserLogin(hashMap)){
               Map<String, Object> idMap = userDao.selectId(hashMap.get("username"));
               return 1==userDao.insertUserId((Integer) idMap.get("id"))?ResultData.success():ResultData.fail();
           }else {
               return ResultData.fail();
           }
        }else {
            return ResultData.fail();
        }
    }
}
