package com.yxq.desmonddossofficeplatform.controller;

import com.yxq.desmonddossofficeplatform.service.UpdatePersonService;
import com.yxq.desmonddossofficeplatform.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author: yxq
 * @Date: 2023/01/17 18:00
 */
@RestController
@RequestMapping("updateInfo")
public class UpdatePersonInfoController {
    @Resource
    UpdatePersonService updatePersonService;

    /**
     * 医生和管理员修改个人信息
     * @param req
     * @return
     */
    @RequestMapping("/info")
    ResultData updatePersonInfo(HttpServletRequest req, HttpSession session){
        Map<String, String[]> parameterMap = req.getParameterMap();
        return updatePersonService.updatePersonInfo(req.getParameterMap());
    }
    /**
     * 医生和管理员查询个人信息
     * @param username
     * @return
     */
    @RequestMapping("/selectInfo")
    ResultData selectPersonInfo(String username){
        return updatePersonService.selectPersonInfo(username);
    }
    /**
     * 用户修改个人信息
     * @param req
     * @return
     */
    @RequestMapping("/userInfo")
    ResultData updateUserPersonInfo(HttpServletRequest req,HttpSession session){
        return updatePersonService.userUpdatePersonInfo(req.getParameterMap());
    }
    /**
     * 用户查询个人信息
     * @param username
     * @return
     */
    @RequestMapping("/userSelectInfo")
    ResultData userSelectPersonInfo(String username){
        return updatePersonService.userSelectPersonInfo(username);
    }

    /**
     * 修改密码
     * @param req
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @RequestMapping("/changePassword")
    ResultData updatePassword(HttpServletRequest req ,HttpSession session, String oldPassword,String newPassword){
        String username = (String) session.getAttribute("username");
        return updatePersonService.changePassword(username,oldPassword,newPassword);
    }
}
