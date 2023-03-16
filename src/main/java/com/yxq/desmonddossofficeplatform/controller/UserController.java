package com.yxq.desmonddossofficeplatform.controller;

import com.yxq.desmonddossofficeplatform.service.UserService;
import com.yxq.desmonddossofficeplatform.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: yxq
 * @Date: 2023/01/20 16:05
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 查询自己的健康信息
     * @param username
     * @return
     */
    @RequestMapping("/selectUserPersonHealthInfo")
    ResultData selectUserPersonHealthInfo(String username){
        return userService.selectUserPersonHealthInfo(username);
    }

    /**
     * 查询自己就诊历史
     * @param username
     * @return
     */
    @RequestMapping("/selectPersonHealthInfo")
    ResultData selectUserPersonHealthHistory(String username){
        return userService.selectUserPersonHealthHistory(username);
    }

    /**
     * 查询家人健康信息
     * @param name
     * @param idCard
     * @return
     */
    @RequestMapping("/familyHealthInfo")
    ResultData selectFamilyHealthInfo(String name,String idCard){
        return userService.selectFamilyHealthInfo(name,idCard);
    }

    /**
     * 查询家人就诊历史
     * @param name
     * @param idCard
     * @return
     */
    @RequestMapping("/familyHealthHistory")
    ResultData selectFamilyHealthHistory(String name,String idCard){
        return userService.selectFamilyHealthHistory(name,idCard);
    }
    @RequestMapping("confirm")
    ResultData insertUserLogin(HttpServletRequest request){
        return userService.insertUserLogin(request.getParameterMap());
    }
}
