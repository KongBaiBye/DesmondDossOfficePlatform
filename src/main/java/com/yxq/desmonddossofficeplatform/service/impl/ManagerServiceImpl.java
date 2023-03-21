package com.yxq.desmonddossofficeplatform.service.impl;

import com.yxq.desmonddossofficeplatform.dao.ManagerDao;
import com.yxq.desmonddossofficeplatform.service.ManagerService;
import com.yxq.desmonddossofficeplatform.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author: yxq
 * @Date: 2023/01/16 10:38
 */
@Service
public class ManagerServiceImpl implements ManagerService {
    @Resource
    ManagerDao managerDao;

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
    public ResultData selectAllUserInfo(String page, String limit) {
        return ResultData.success(managerDao.selectAllUserInfoCount(),
                managerDao.selectAllUserInfo((Integer.parseInt(page) - 1) * 10, Integer.parseInt(limit)));
    }

    @Override
    public ResultData selectAllDoctorInfo(String page, String limit) {
        return ResultData.success(managerDao.selectAllDoctorInfoCount(),
                managerDao.selectAllDoctorInfo((Integer.parseInt(page) - 1) * 10, Integer.parseInt(limit)));
    }

    @Override
    public ResultData selectAllDepartmentDoctor(String page, String limit) {
        return ResultData.success(managerDao.selectAllDoctorInfoCount(),
                managerDao.selectAllDepartmentDoctor((Integer.parseInt(page) - 1) * 10, Integer.parseInt(limit)));
    }

    @Override
    public ResultData selectAllDrugInfo(String page, String limit) {
        return ResultData.success(managerDao.selectDrugInfoCount(),
                managerDao.selectAllDrugInfo((Integer.parseInt(page) - 1) * 10, Integer.parseInt(limit)));
    }

    @Override
    public ResultData selectLoginLog(String page, String limit) {
        return ResultData.success(managerDao.selectLoginLogCount(),
                managerDao.selectLoginLog((Integer.parseInt(page) - 1) * 10, Integer.parseInt(limit)));
    }


    @Override
    public ResultData selectDoctorPersonInfo(String name) {
        return ResultData.success(managerDao.selectDoctorPersonInfo(name));
    }

    @Override
    public ResultData insertDoctorInfo(Map<String, String[]> map) {
        return 1 == managerDao.insertDoctorInfo(conversion(map)) ? ResultData.success() : ResultData.fail();
    }

    @Override
    public ResultData updateDoctorInfo(Map<String, String[]> map) {
        return 1 == managerDao.updateDoctorInfo(conversion(map)) ? ResultData.success() : ResultData.fail();
    }

    @Override
    public ResultData removeDoctorInfo(String empno) {
        return 1 == managerDao.removeDoctorInfo(empno) ? ResultData.success() : ResultData.fail();
    }

    @Override
    public ResultData updatePassword(String empno) {
        return 1 == managerDao.updatePassword(empno) ? ResultData.success() : ResultData.fail();
    }

    @Override
    public ResultData selectUserInfo(String name) {
        return ResultData.success(managerDao.selectUserInfo(name));
    }

    @Override
    public ResultData insertUserInfo(Map<String, String[]> map) {
        return 1 == managerDao.insertUserInfo(conversion(map)) ? ResultData.success() : ResultData.fail();
    }

    @Override
    public ResultData updateUserInfo(Map<String, String[]> map) {
        return 1 == managerDao.updateUserInfo(conversion(map)) ? ResultData.success() : ResultData.fail();
    }

    @Override
    public ResultData removeUserInfo(String id) {
        return 1 == managerDao.removeUserInfo(Integer.parseInt(id)) ? ResultData.success() : ResultData.fail();
    }

    @Override
    public ResultData updateUserPassword(String idCard) {
        return 1 == managerDao.updateUserPassword(idCard) ? ResultData.success() : ResultData.fail();
    }

    @Override
    public ResultData updateDoctorDepartmentOut(String empno) {
        return 1 == managerDao.updateDoctorDepartmentOut(empno) ? ResultData.success() : ResultData.fail();
    }

    @Override
    public ResultData updateDoctorDepartmentOn(String empno, String department) {
        return 1 == managerDao.updateDoctorDepartmentOn(empno, department) ? ResultData.success() : ResultData.fail();
    }

    @Override
    public ResultData selectDrugInfo(String drugName) {
        return ResultData.success(managerDao.selectDrugInfo(drugName));
    }

    @Override
    public ResultData selectSymptom(String symptom) {
        return ResultData.success(managerDao.selectSymptom("%" + symptom + "%"));
    }
    @Override
    public ResultData insertDrug(Map<String, String[]> map) {
        return 1 == managerDao.insertDrug(conversion(map))?ResultData.success():ResultData.fail();
    }

    @Override
    public ResultData removeDrug(String id) {
        return 1 == managerDao.removeDrug(Integer.parseInt(id))? ResultData.success():ResultData.fail();
    }

    @Override
    public ResultData updateDrugInfo(Map<String, String[]> map) {
        return 1 == managerDao.updateDrugInfo(conversion(map))?ResultData.success():ResultData.fail();
    }

    @Override
    public ResultData selectAllOperationLog(String page,String limit) {
        return ResultData.success(managerDao.selectOperLogCount(),managerDao.selectAllOperationLog((Integer.parseInt(page) - 1) * 10, Integer.parseInt(limit)));
    }
    @Override
    public ResultData selectDrugLog(String page, String limit) {
        return ResultData.success(managerDao.selectDrugLogCount(),
                managerDao.selectDrugLog((Integer.parseInt(page) - 1) * 10, Integer.parseInt(limit)));
    }

}
