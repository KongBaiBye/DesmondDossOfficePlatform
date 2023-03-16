package com.yxq.desmonddossofficeplatform.dao;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: yxq
 * @Date: 2023/01/17 10:42
 */
@Mapper
public interface DoctorDao {
    /**
     * 查询全部的居民健康信息
     * @param page
     * @param limit
     * @return
     */
    @Select("select hi.id,ui.name,ui.idCard,hi.height,hi.weight,ui.medicareCard," +
            "hi.bloodGlucose,hi.bloodOxygen,hi.bloodPressure," +
            "hi.heartbeat,hi.pulse,hi.examineDate " +
            "from health_info hi left join " +
            "user_info ui on hi.medicareCardId = ui.medicareCard limit #{page},#{limit};")
    List<Map<String,Object>> selectAllUserHealthInfo(@Param("page") int page,@Param("limit") int limit);

    /**
     * 查询全部居民健康信息的数量
     * @return
     */
    @Select("select count(id) from health_info;")
    int selectAllUserHealthIndoCount();

    /**
     * 查询全部居民就诊历史
     * @param page
     * @param limit
     * @return
     */
    @Select("select ui.name,ui.idCard,mh.symptom,mh.creatTime from " +
            "user_info ui left join medical_history mh " +
            "on ui.medicareCard = mh.medicareCardId limit #{page},#{limit}")
    List<Map<String,Object>> selectAllUserHealthHistory(@Param("page") int page,@Param("limit") int limit);
    /**
     * 查询全部居民就诊历史的数量
     * @return
     */
    @Select("select count(id) from medical_history;")
    int selectAllUserHealthHistoryCount();

    /**
     * 根据姓名搜索用户健康信息
     * @param name
     * @return
     */
    @Select("select ui.name,ui.idCard,hi.height,hi.weight,ui.medicareCard," +
            "hi.bloodGlucose,hi.bloodOxygen,hi.bloodPressure," +
            "hi.heartbeat,hi.pulse,hi.examineDate " +
            "from health_info hi left join " +
            "user_info ui on hi.medicareCardId = ui.medicareCard where ui.name = #{name}")
    List<Map<String,Object>> selectUserPersonInfo(@Param("name") String name);

    /**
     * 第一部分，把用户健康信息添加到健康表
     * 添加用户健康信息
     * @param map
     * @return
     */
    @Insert("insert into health_info(height, weight, bloodPressure, heartbeat, pulse," +
            " bloodGlucose, examineDate, bloodOxygen,medicareCardId)" +
            "VALUES(#{height},#{weight},#{bloodPressure},#{heartbeat}," +
            "#{pulse},#{bloodGlucose},#{examineDate},#{height},#{medicareCard}) ")
    @Results(
            value = {
                    @Result(column = "medicareCardId", property = "medicareCard")
            }
    )
    int insertUserHealthInfo(Map<String,Object> map);

    /**
     * 第二步，把用户姓名，和身份证号添加的用户表
     * @param map
     * @return
     */
    @Insert("insert into user_info set name = #{name},idCard = #{idCard},medicareCard = #{medicareCard}")
    int insertUserInfo(Map<String,Object> map);

    /**
     * 删除用户健康信息
     * @param id
     * @return
     */
    @Update("update health_info set height = 'null',weight = 'null',bloodPressure = 'null'," +
            "heartbeat = 'null',pulse = 'null',bloodGlucose = 'null'," +
            "bloodOxygen = 'null' where id = #{id}")
    int removeUserHealthInfo(@Param("id") int id);

    /**
     * 修改健康信息
     * @param map
     * @return
     */
    @Update("update health_info set height = #{height},weight = #{weight},bloodPressure = #{bloodPressure}," +
            "heartbeat = #{heartbeat},pulse = #{pulse},bloodGlucose = #{bloodGlucose},examineDate = #{examineDate}," +
            "bloodOxygen = #{bloodOxygen} where id = #{id}")
    int updateUserHealthInfo(Map<String,Object> map);

    /**
     * 根据姓名查搜索就诊历史1
     * @param name
     * @return
     */
    @Select("select ui.name,ui.idCard,mh.symptom,mh.creatTime from user_info ui " +
            "left join medical_history mh on ui.medicareCard = mh.medicareCardId where ui.name = #{name}")
    List<Map<String,Object>> selectUserHealthHistory1(@Param("name") String name);

    /**
     * 根据时间搜索就诊历史2
     * @param onTime
     * @param outTime
     * @return
     */
    @Select("select ui.name,ui.idCard,mh.symptom,mh.creatTime from user_info ui " +
            "left join medical_history mh on ui.medicareCard = mh.medicareCardId where mh.creatTime between #{onTime} and #{outTime} ")
    List<Map<String,Object>> selectUserHealthHistory2(@Param("onTime") String onTime,@Param("outTime") String outTime);

    /**
     * 根据身份证号查3
     * @param idCard
     * @return
     */
    @Select("select ui.name,ui.idCard,mh.symptom,mh.creatTime from user_info ui " +
            "left join medical_history mh on ui.medicareCard = mh.medicareCardId where ui.idCard = #{idCard}")
    List<Map<String,Object>> selectUserHealthHistory3(@Param("idCard") String idCard);

    /**
     * 根据身份证号加姓名查4
     * @param idCard
     * @param name
     * @return
     */
    @Select("select ui.name,ui.idCard,mh.symptom,mh.creatTime from user_info ui " +
            "left join medical_history mh on ui.medicareCard = mh.medicareCardId where ui.idCard = #{idCard} and ui.name = #{name}")
    List<Map<String,Object>> selectUserHealthHistory4(@Param("idCard") String idCard,@Param("name") String name);
    /**
     * 根据姓名查搜索就诊历史5
     * @param name
     * @param onTime
     * @param outTime
     * @return
     */
    @Select("select ui.name,ui.idCard,mh.symptom,mh.creatTime from user_info ui " +
            "left join medical_history mh on ui.medicareCard = mh.medicareCardId where ui.name = #{name} and mh.creatTime between #{onTime} and #{outTime} ")
    List<Map<String,Object>> selectUserHealthHistory5(@Param("name") String name,@Param("onTime") String onTime,@Param("outTime") String outTime);

    /**
     * 根据姓名查搜索就诊历史6
     * @param name
     * @param onTime
     * @param outTime
     * @param idCard
     * @return
     */
    @Select("select ui.name,ui.idCard,mh.symptom,mh.creatTime from user_info ui " +
            "left join medical_history mh on ui.medicareCard = mh.medicareCardId where ui.idCard = #{idCard} and ui.name = #{name} and mh.creatTime between #{onTime} and #{outTime} ")
    List<Map<String,Object>> selectUserHealthHistory6(@Param("name") String name,@Param("onTime") String onTime,@Param("outTime") String outTime,@Param("idCard") String idCard);
    /**
     * 查询医保卡信息
     * @param idCard
     * @return
     */
    @Select("select ui.name,ui.idCard,ui.medicareCard,mc.balance from " +
            "user_info ui left join medicare_card mc " +
            "on ui.medicareCard = mc.medicareCard " +
            "where ui.idCard = #{idCard}")
    List<Map<String,Object>> selectMedicareCard(String idCard);
    /**
     * 添加药品信息到病历单
     * @param map
     * @return
     */
    @Update("insert into medical_record(id, drugName, drugPrice, number) " +
            "VALUES(#{id},#{drugName},#{drugPrice},#{number}) ")
    int addDrugGoMedical(Map<String,Object> map);

    /**
     * 查询病历单
     * @return
     */
    @Select("select id,drugName,drugPrice,number,drugPrice*number sum from medical_record")
    List<Map<String,Object>> selectDrugMedical();

    /**
     * 删除病历单药品
     * @param id
     * @return
     */
    @Delete("delete from medical_record where id = #{id}")
    int removeDrug(String id);
    /**
     *
     * 查询医生工号
     * @param doctorName
     * @return
     */
    @Select("select empno from doctor_info where name = #{doctorName}")
    String selectDoctorCard(@Param("doctorName") String doctorName);

    /**
     * 门诊日志
     * @param map
     * @return
     */
    @Insert("insert into outpatient_log(patientName, idCard, cost, payOrNot, creatTime, doctorId) " +
            "VALUES(#{name},#{idCard},#{sum},0,now(),#{doctorId}) ")
    int insertOutpatient(Map<String,Object> map);

    /**
     * 给就诊历史添加信息
     * @param map
     * @return
     */
    @Insert("insert into medical_history(symptom, creatTime, medicareCardId) " +
            "VALUES (#{symptom},now(),#{medicareCard})")
    int insertUserMedicalHistory(Map<String,Object> map);

    /**
     * 清空整张病历单表
     * @return
     */
    @Delete("truncate medical_record")
    int removeRecordInfo();

    /**
     * 查询门诊日志
     * @return
     */
    @Select("select *from outpatient_log")
    List<Map<String,Object>> selectOutpatientLog();

    /**
     * 修改缴费状态
     * @param map
     * @return
     */
    @Update(" update outpatient_log set payOrNot = 1 where patientName = #{patientName} and idCard = #{idCard}")
    int updatePay(Map<String,Object> map);



}
