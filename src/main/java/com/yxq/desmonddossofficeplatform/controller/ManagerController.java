package com.yxq.desmonddossofficeplatform.controller;
import com.yxq.desmonddossofficeplatform.service.ManagerService;
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
 * @Date: 2023/01/16 10:37
 */
@RestController
@RequestMapping("/admin")
public class ManagerController {
   @Resource
    ManagerService managerService;

    /**
     * 查询全部的用户信息
     *
     * @param page
     * @param limit
     * @return
     */
    //http://localhost:8080/admin/info
    @RequestMapping("info")
    public ResultData selectAllUserInfo(String page, String limit) {
        return managerService.selectAllUserInfo(page, limit);
    }

    /**
     * 查询全部的医生信息
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/doctorInfo")
    public ResultData selectAllDoctorInfo(String page, String limit) {
        return managerService.selectAllDoctorInfo(page, limit);
    }

    /**
     * 查询全部科室的医生信息
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/departmentDoctor")
    public ResultData selectAllDepartmentDoctor(String page, String limit) {
        return managerService.selectAllDepartmentDoctor(page, limit);
    }

    @RequestMapping("/drugInfo")
    public ResultData selectAllDrugInfo(String page, String limit) {
        return managerService.selectAllDrugInfo(page, limit);
    }

    /**
     * 查询全部的登录日志
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/loginLog")
    public ResultData selectLoginLog(String page, String limit) {
        return managerService.selectLoginLog(page, limit);
    }

    /**
     * 查询全部的药品日志
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/drugLog")
    public ResultData selectDrugLog(String page, String limit) {
        return managerService.selectDrugLog(page, limit);
    }

    /**
     * 查询全部的医生信息
     *
     * @return
     */
    @RequestMapping("/doctorPersonInfo")
    public ResultData selectDoctorPersonInfo(String name) {
        return managerService.selectDoctorPersonInfo(name);
    }

    /**
     * 添加医生
     *
     * @param req
     * @return
     */
    @RequestMapping("/insertDoctor")
    public ResultData insertDoctorInfo(HttpServletRequest req,HttpSession session) {
        return managerService.insertDoctorInfo(req.getParameterMap());
    }

    /**
     * 修改医生信息
     *
     * @param req
     * @return
     */
    @RequestMapping("/updateDoctor")
    public ResultData updateDoctorInfo(HttpServletRequest req,HttpSession session) {
        return managerService.updateDoctorInfo(req.getParameterMap());
    }

    /**
     * 删除医生信息
     *
     * @param empno
     * @return
     */
    @RequestMapping("/removeDoctor")
    public ResultData removeDoctorInfo(String empno,HttpSession session) {
        return managerService.removeDoctorInfo(empno);
    }

    /**
     * 重置密码
     *
     * @param empno
     * @return
     */
    @RequestMapping("updatePassword")
    public ResultData resetPassword(String empno) {
        return managerService.updatePassword(empno);
    }

    /**
     * 搜索用户信息
     *
     * @param name
     * @return
     */
    @RequestMapping("/userInfo")
    public ResultData selectUserInfo(String name) {
        return managerService.selectUserInfo(name);
    }

    /**
     * 添加用户信息
     *
     * @param req
     * @return
     */
    @RequestMapping("insertUserInfo")
    public ResultData insertUserInfo(HttpServletRequest req, HttpSession session) {
        return managerService.insertUserInfo(req.getParameterMap());
    }

    /**
     * 修改用户信息
     *
     * @param req
     * @return
     */
    @RequestMapping("updateUserInfo")
    public ResultData updateUserInfo(HttpServletRequest req,HttpSession session) {
        return managerService.updateUserInfo(req.getParameterMap());
    }

    /**
     * 删除用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping("removeUserInfo")
    public ResultData removeUserInfo(String id,HttpSession session) {
        return managerService.removeUserInfo(id);
    }

    /**
     * 重置用户密码
     *
     * @param idCard
     * @return
     */
    @RequestMapping("/updateUserPassword")
    public ResultData resetUserPassword(String idCard) {
        return managerService.updateUserPassword(idCard);
    }

    /**
     * 移出科室功能，根据id把科室department置为 未分配
     *
     * @param empno
     * @return
     */
    @RequestMapping("/updateDoctorDepartmentOut")
    public ResultData shiftOutDoctorDepartmentOut(String empno) {
        return managerService.updateDoctorDepartmentOut(empno);
    }

    /**
     * 移入科室
     *
     * @param empno
     * @param department
     * @return
     */
    @RequestMapping("/updateDoctorDepartmentOn")
    public ResultData shiftInDoctorDepartmentOn(String empno, String department) {
        return managerService.updateDoctorDepartmentOn(empno, department);
    }

    /**
     * 根据药品名，查询药品信息
     *
     * @param drugName
     * @return
     */
    @RequestMapping("/selectDrugInfo")
    public ResultData selectDrugInfo(String drugName) {
        return managerService.selectDrugInfo(drugName);
    }

    /**
     * 根据症状模糊查询药品
     *
     * @param symptom
     * @return
     */
    @RequestMapping("/selectSymptom")
    public ResultData selectSymptom(String symptom) {
        return managerService.selectSymptom(symptom);
    }

    /**
     * 添加药品信息
     * @param req
     * @return
     */
    @RequestMapping("/insertDrug")
    public ResultData insertDrug(HttpServletRequest req,HttpSession session){
        Map<String, String[]> parameterMap = req.getParameterMap();
        return managerService.insertDrug(req.getParameterMap());
    }

    /**
     * 删除药品信息
     * @param id
     * @return
     */
    @RequestMapping("/removeDrug")
    public ResultData removeDrug(String id){
        return managerService.removeDrug(id);
    }

    /**
     * 修改药品信息
     * @param req
     * @return
     */
    @RequestMapping("/updateDrugInfo")
    public ResultData updateDrugInfo(HttpServletRequest req,HttpSession session){
        return managerService.updateDrugInfo(req.getParameterMap());
    }

    /**
     * 查询全部的操作日志
     * @return
     */
    @RequestMapping("/selectAllOperationLog")
    public ResultData selectAllOperationLog(){
        return managerService.selectAllOperationLog();
    }
}
