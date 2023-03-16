package com.yxq.desmonddossofficeplatform.service.impl;

import com.yxq.desmonddossofficeplatform.dao.DoctorDao;
import com.yxq.desmonddossofficeplatform.service.DoctorService;
import com.yxq.desmonddossofficeplatform.utils.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yxq
 * @Date: 2023/01/17 10:43
 */
@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    @Resource
    private DoctorDao doctorDao;

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
    public ResultData selectAllUserHealthInfo(String page, String limit) {
        return ResultData.success(doctorDao.selectAllUserHealthIndoCount(),
                doctorDao.selectAllUserHealthInfo((Integer.parseInt(page) - 1) * 10, Integer.parseInt(limit)));
    }

    @Override
    public ResultData selectAllUserHealthHistory(String page, String limit) {
        return ResultData.success(doctorDao.selectAllUserHealthHistoryCount(),
                doctorDao.selectAllUserHealthHistory((Integer.parseInt(page) - 1) * 10, Integer.parseInt(limit)));
    }

    @Override
    public ResultData selectUserPersonInfo(String name) {
        return ResultData.success(doctorDao.selectUserPersonInfo(name));
    }

    @Override
    public ResultData insertUserHealthInfo(Map<String, String[]> map) {
        return 1 == doctorDao.insertUserHealthInfo(conversion(map))
                && 1 == doctorDao.insertUserInfo(conversion(map)) ? ResultData.success() : ResultData.fail();
    }

    @Override
    public ResultData removeUserHealthInfo(String id) {
        return 1 == doctorDao.removeUserHealthInfo(Integer.parseInt(id)) ? ResultData.success() : ResultData.fail();
    }

    @Override
    public ResultData updateUserHealthInfo(Map<String, String[]> map) {
        return 1 == doctorDao.updateUserHealthInfo(conversion(map)) ? ResultData.success() : ResultData.fail();
    }

    @Override
    public ResultData selectUserHealthHistory(String name, String idCard, String onTime, String outTime) {
        if (name != "" && idCard == "" && onTime == "" && onTime == "") {
            //1
            return ResultData.success(doctorDao.selectUserHealthHistory1(name));
        } else if (name == "" && idCard == "" && onTime != "" && onTime != "") {
            //2
            return ResultData.success(doctorDao.selectUserHealthHistory2(onTime, outTime));
        } else if (name == "" && idCard != "" && onTime == "" && onTime == "") {
            //3
            return ResultData.success(doctorDao.selectUserHealthHistory3(idCard));
        } else if (name != "" && idCard != "" && onTime == "" && onTime == "") {
            //4
            return ResultData.success(doctorDao.selectUserHealthHistory4(idCard, name));
        } else if (name != "" && idCard == "" && onTime != "" && onTime != "") {
            //5
            return ResultData.success(doctorDao.selectUserHealthHistory5(name, onTime, outTime));
        } else if (name != "" && idCard != "" && onTime != "" && onTime != "") {
            //6
            return ResultData.success(doctorDao.selectUserHealthHistory6(name, onTime, outTime, idCard));
        } else {
            return ResultData.fail();
        }
    }

    @Override
    public ResultData selectMedicareCard(String idCard) {
        return ResultData.success(doctorDao.selectMedicareCard(idCard));
    }

    @Override
    public ResultData addDrugGoMedical(Map<String, String[]> map) {
        return 1 == doctorDao.addDrugGoMedical(conversion(map)) ? ResultData.success() : ResultData.fail();
    }

    @Override
    public ResultData selectDrugMedical() {
        return ResultData.success(doctorDao.selectDrugMedical());
    }

    @Override
    public ResultData removeDrug(String id) {
        return 1 == doctorDao.removeDrug(id) ? ResultData.success() : ResultData.fail();
    }

    @Override
    public ResultData commit(Map<String, String[]> map) {
        Map<String, Object> conversion = conversion(map);
        Object idCard = conversion.get("idCard");
        List<Map<String, Object>> list = doctorDao.selectDrugMedical();
        //总金额
        double cost = 0;
        for (Map<String, Object> drugMap : list) {
           double sum = (double) drugMap.get("sum");
            cost = cost + sum;
        }
        conversion.put("sum", cost);
        //查询医生工号
        String doctorId = doctorDao.selectDoctorCard((String) conversion.get("doctorName"));

        conversion.put("medicareCard", idCard);
        conversion.put("doctorId", doctorId);
        int row = doctorDao.insertUserMedicalHistory(conversion);
        int i = doctorDao.removeRecordInfo();
        return  1 == doctorDao.insertOutpatient(conversion) ? ResultData.success() : ResultData.fail();

    }

    @Override
    public ResultData selectOutpatientLog() {
        return ResultData.success(doctorDao.selectOutpatientLog());
    }

    @Override
    public ResultData updatePay(Map<String, String[]> map) {
        return 1 == doctorDao.updatePay(conversion(map))?ResultData.success():ResultData.fail();
    }
}
