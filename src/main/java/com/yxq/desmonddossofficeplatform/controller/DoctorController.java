package com.yxq.desmonddossofficeplatform.controller;

import com.yxq.desmonddossofficeplatform.service.DoctorService;
import com.yxq.desmonddossofficeplatform.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @Author: yxq
 * @Date: 2023/01/17 10:41
 */
@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Resource
    DoctorService doctorService;
    private final List<Map<String, Object>> list = null;

    /**
     * 查询全部居民的健康信息
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("userHealthInfo")
    public ResultData selectAllUserHealthInfo(String page, String limit) {
        return doctorService.selectAllUserHealthInfo(page, limit);
    }

    /**
     * 查询全部居民的就诊历史
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("healthHistory")
    public ResultData selectAllUserHealthHistory(String page, String limit) {
        return doctorService.selectAllUserHealthHistory(page, limit);
    }

    /**
     * 查询用户健康信息
     *
     * @param name
     * @return
     */
    @RequestMapping("/userPersonInfo")
    public ResultData selectUserPersonInfo(String name) {
        return doctorService.selectUserPersonInfo(name);
    }

    /**
     * 添加用户信息
     *
     * @param req
     * @return
     */
    @RequestMapping("/insertHealthInfo")
    public ResultData insertUserHealthInfo(HttpServletRequest req, HttpSession session) {
        return doctorService.insertUserHealthInfo(req.getParameterMap());
    }

    /**
     * 删除用户健康信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/removeUserHealthInfo")
    public ResultData removeUserHealthInfo(String id) {
        return doctorService.removeUserHealthInfo(id);
    }

    /**
     * 修改用户健康信息
     *
     * @param req
     * @return
     */
    @RequestMapping("/updateUserHealthInfo")
    public ResultData updateUserHealthInfo(HttpServletRequest req, HttpSession session) {
        return doctorService.updateUserHealthInfo(req.getParameterMap());
    }

    /**
     * 查询看病历史
     *
     * @param name
     * @param onTime
     * @param outTime
     * @return
     */
    @RequestMapping("/selectHistory")
    public ResultData selectUserHealthHistory(String name, String idCard, String onTime, String outTime) {
        return doctorService.selectUserHealthHistory(name, idCard, onTime, onTime);
    }

    /**
     * 查询医保卡信息
     *
     * @param idCard
     * @return
     */
    @RequestMapping("/selectMedicareCard")
    public ResultData selectMedicareCard(String idCard) {
        return doctorService.selectMedicareCard(idCard);
    }

    /**
     * 病历单
     *
     * @return
     */
    @RequestMapping("/medicalRecord")
    public ResultData medicalRecord(HttpServletRequest req) {
        return doctorService.addDrugGoMedical(req.getParameterMap());
    }

    /**
     * 病历单
     *
     * @return
     */
    @RequestMapping("/selectMedicalRecord")
    public ResultData selectMedicalRecord(HttpServletRequest req) {
        return doctorService.selectDrugMedical();
    }

    /**
     * 删除病历单中的药品
     *
     * @param id
     * @return
     */
    @RequestMapping("/removeDrug")
    public ResultData removeDrug(String id) {
        return doctorService.removeDrug(id);
    }

    /**
     * 病历单提交
     *
     * @param req
     * @return
     */
    @RequestMapping("/commit")
    public ResultData commit(HttpServletRequest req) {
        Map<String, String[]> parameterMap = req.getParameterMap();
        return doctorService.commit(parameterMap);
    }

    /**
     * 门诊日志
     *
     * @return
     */
    @RequestMapping("/outpatientLog")
    public ResultData selectOutpatientLog() {
        return doctorService.selectOutpatientLog();
    }

    @RequestMapping("/outPay")
    public ResultData outPay(HttpServletRequest req) {
        Map<String, String[]> map = req.getParameterMap();
        return doctorService.updatePay(map);
    }
}
