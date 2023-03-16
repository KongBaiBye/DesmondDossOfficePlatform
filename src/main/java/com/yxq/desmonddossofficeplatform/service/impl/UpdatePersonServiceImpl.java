package com.yxq.desmonddossofficeplatform.service.impl;

import com.yxq.desmonddossofficeplatform.dao.UpdatePersonDao;
import com.yxq.desmonddossofficeplatform.service.UpdatePersonService;
import com.yxq.desmonddossofficeplatform.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yxq
 * @Date: 2023/01/17 17:54
 */
@Service
public class UpdatePersonServiceImpl implements UpdatePersonService {
    @Resource
    UpdatePersonDao updatePersonDao;
    @Override
    public ResultData updatePersonInfo(Map<String, String[]> map) {
        HashMap<String, Object> hashMap = new HashMap<>();
        map.forEach((k,v)->hashMap.put(k,v[0]));
        int row = updatePersonDao.updatePersonInfo(hashMap);
        return row == 1 ? ResultData.success() : ResultData.fail();
    }

    @Override
    public ResultData selectPersonInfo(String username) {
        return ResultData.success(updatePersonDao.selectPersonInfo(username));
    }

    @Override
    public ResultData userUpdatePersonInfo(Map<String, String[]> map) {
        HashMap<String, Object> hashMap = new HashMap<>();
        map.forEach((k,v)->hashMap.put(k,v[0]));
        int row = updatePersonDao.userUpdatePersonInfo(hashMap);
        return row == 1 ? ResultData.success() : ResultData.fail();
    }
    @Override
    public ResultData userSelectPersonInfo(String username) {
        return ResultData.success(updatePersonDao.userSelectPersonInfo(username));
    }

    @Override
    public ResultData changePassword(String username, String oldPassword, String newPassword) {
        String oldDbPassword = updatePersonDao.selectPassword(username);
        if (oldDbPassword.equals(oldPassword)){
            //如果老密码正确执行修改操作
            return 1 == updatePersonDao.changePassword(username,newPassword)?ResultData.success():ResultData.fail();
        }else {
            return ResultData.success("密码错误");
        }
    }
}
