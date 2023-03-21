package com.yxq.desmonddossofficeplatform.dao;

import com.yxq.desmonddossofficeplatform.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: yxq
 * @Date: 2023/01/16 10:39
 */
@Mapper
public interface ManagerDao {
    /**
     * Dao层查询所有用户的信息
     *
     * @return 全部用户的信息
     */
    @Select("select *from user_info limit #{page},#{limit}")
    List<User> selectAllUserInfo(@Param("page") int page, @Param("limit") int limit);

    /**
     * 查询用户表有多少条数据
     *
     * @return 用户的数量
     */
    @Select("select count(id) from user_info")
    int selectAllUserInfoCount();

    /**
     * Dao层查询全部医生的信息
     *
     * @return 全部医生的信息
     */
    @Select("select di.id,di.name,di.age,di.gender,di.idCard,di.phone,di.address,di.empno,di.userId,dp.positionName,hd.departmentName from" +
            " doctor_info di left join doctor_position dp " +
            "on di.positionId = dp.id  " +
            "left join hospital_department hd " +
            "on di.department = hd.id limit #{page},#{limit}")
    List<Map<String, Object>> selectAllDoctorInfo(@Param("page") int page, @Param("limit") int limit);

    /**
     * 查询医生表有多少条数据
     *
     * @return 医生的数量
     */
    @Select("select count(id) from doctor_info")
    int selectAllDoctorInfoCount();

    /**
     * 查询全部科室的所以医生
     *
     * @return 全部科室的医生
     */
    @Select("select di.name,di.age,di.gender,di.phone,di.empno,dp.positionName,hd.departmentName from" +
            " doctor_info di left join doctor_position dp " +
            "on di.positionId = dp.id  " +
            "left join hospital_department hd " +
            "on di.department = hd.id order by hd.departmentName limit #{page},#{limit}")
    List<Map<String, Object>> selectAllDepartmentDoctor(@Param("page") int page, @Param("limit") int limit);

    /**
     * 查询全部的药品信息
     *
     * @param page
     * @param limit
     * @return
     */
    @Select("select *from drug_info limit #{page},#{limit}")
    List<Map<String, Object>> selectAllDrugInfo(@Param("page") int page, @Param("limit") int limit);

    /**
     * 查询药品的数量
     *
     * @return 日志的条数
     */
    @Select("select count(id) from drug_info")
    int selectDrugInfoCount();

    /**
     * 查询全部登录日志
     */
    @Select("select *from login_log limit #{page},#{limit}")
    List<Map<String, Object>> selectLoginLog(@Param("page") int page, @Param("limit") int limit);

    /**
     * 查询日志的条数
     *
     * @return 日志的条数
     */
    @Select("select count(id) from login_log")
    int selectLoginLogCount();

    /**
     * 查询全部药品日志
     */
    @Select("select *from drug_log limit #{page},#{limit}")
    List<Map<String, Object>> selectDrugLog(@Param("page") int page, @Param("limit") int limit);

    /**
     * 查询药品日志的条数
     *
     * @return 日志的条数
     */
    @Select("select count(id) from drug_log")
    int selectDrugLogCount();

    /**
     * 根据姓名搜索医生信息
     *
     * @param name
     * @return
     */
    @Select("select di.id,di.name,di.age,di.gender,di.idCard,di.phone,di.address,di.empno,dp.positionName,hd.departmentName from" +
            " doctor_info di left join doctor_position dp " +
            "on di.positionId = dp.id  " +
            "left join hospital_department hd " +
            "on di.positionId = hd.id where di.name = #{name}")
    List<Map<String, Object>> selectDoctorPersonInfo(@Param("name") String name);

    /**
     * 添加医生信息
     *
     * @param map
     * @return
     */
    @Insert("insert into doctor_info set name=#{name},age=#{age},gender=#{gender},idCard=#{idCard},phone=#{phone},address=#{address}," +
            "empno=#{empno},positionId=#{positionId},department=#{department}")
    int insertDoctorInfo(Map<String, Object> map);

    /**
     * 根据id修改医生信息
     *
     * @param map
     * @return
     */
    @Update("update doctor_info set name=#{name},age=#{age},gender=#{gender}," +
            "idCard=#{idCard},phone=#{phone},address=#{address},empno=#{empno}," +
            "positionId=#{positionId},department=#{department} where id = #{id}")
    int updateDoctorInfo(Map<String, Object> map);

    /**
     * 根据工号删除医生信息
     *
     * @param empno
     * @return
     */
    @Delete("delete from doctor_info where empno = #{empno}")
    int removeDoctorInfo(@Param("empno") String empno);

    /**
     * 根据工号重置医生密码
     *
     * @param empno
     * @return
     */
    @Update("update user_login set  password = '000000' " +
            "where userId =  (select userId from doctor_info where empno = #{empno})")
    int updatePassword(String empno);

    /**
     * 根据姓名查询用户信息
     *
     * @param name
     * @return
     */
    @Select("select *from user_info where name = #{name}")
    List<Map<String, Object>> selectUserInfo(@Param("name") String name);

    /**
     * 添加用户信息
     *
     * @param map
     * @return
     */
    @Insert("insert into user_info(name, age, gender, idCard, medicareCard, phone, address,userId) " +
            "VALUES (#{name},#{age},#{gender},#{idCard},#{medicareCard},#{phone},#{address},userId=id)")
    int insertUserInfo(Map<String, Object> map);

    /**
     * 根据身份证号更新用户信息
     *
     * @param map
     * @return
     */
    @Update("update user_info set name = #{name},age = #{age},gender = #{gender},idCard = #{idCard}," +
            "medicareCard = #{medicareCard},phone = #{phone},address = #{address} where id=#{id}")
    int updateUserInfo(Map<String, Object> map);

    /**
     * 根据id用户信息
     *
     * @param id
     * @return
     */
    @Delete("delete from user_info where id = #{id}")
    int removeUserInfo(int id);


    /**
     * 根据工号重置医生密码
     *
     * @param idCard
     * @return
     */
    @Update("update user_login set password = '000000' " +
            "where id = (select userId from user_info where idCard = #{idCard})")
    int updateUserPassword(String idCard);

    /**
     * 移出科室功能，根据id把科室department置位null
     *
     * @param empno
     * @return
     */
    @Update("update doctor_info set department = 16 where empno = #{empno}")
    int updateDoctorDepartmentOut(@Param("empno") String empno);

    /**
     * 移入科室功能
     *
     * @param empno
     * @param department
     * @return
     */
    @Update("update doctor_info set department = #{department} where empno = #{empno}")
    int updateDoctorDepartmentOn(@Param("empno") String empno, @Param("department") String department);

    /**
     * 根据药品名查询药品信息
     *
     * @param drugName
     * @return
     */
    @Select("select *from drug_info where drugName = #{drugName}")
    List<Map<String, Object>> selectDrugInfo(@Param("drugName") String drugName);

    /**
     * 根据症状模糊搜索
     *
     * @param symptom
     * @return
     */
    @Select("select *from drug_info where symptom like #{symptom}")
    List<Map<String, Object>> selectSymptom(@Param("symptom") String symptom);

    /**
     * 添加药品
     * @param map
     * @return
     */
    @Insert("insert into drug_info(drugName, vender, symptom, drugPrice, drugCount, onTime) " +
            "VALUES(#{drugName},#{vender},#{symptom},#{drugPrice},#{drugCount},#{onTime}) ")
    int insertDrug(Map<String,Object> map);

    /**
     * 删除药品
     * @param id
     * @return
     */
    @Delete("delete from drug_info where id = #{id}")
    int removeDrug(@Param("id") int id);

    /**
     * 根据id修改药品信息
     * @param map
     * @return
     */
    @Update("update drug_info set drugName = #{drugName},drugName = #{drugName},drugName = #{drugName}," +
            "drugName = #{drugName},drugName = #{drugName},drugName = #{drugName},drugName = #{drugName} where id = #{id}")
    int updateDrugInfo(Map<String,Object> map);

    /**
     * 查询全部的操作日志
     * @return
     */
    @Select("select *from operation_log limit #{limit},#{size}")
    List<Map<String,Object>> selectAllOperationLog(Integer limit,Integer size);

    /**
     * 根据id查询药品名
     *
     * @param id id
     * @return {@link Map}<{@link String},{@link Object}>
     */
    @Select("select drugName from drug_info where id = #{id}")
    Map<String,Object> selectDrugById(Integer id);

    /**
     * 根据id查询用户名
     *
     * @param id id
     * @return {@link Map}<{@link String},{@link Object}>
     */
    @Select("select name from user_info where id = #{id}")
    Map<String,Object> selectUserName(Integer id);

    /**
     * 根据医生id用户名
     *
     * @param id id
     * @return {@link Map}<{@link String},{@link Object}>
     */
    @Select("select name from doctor_info where id = #{id}")
    Map<String,Object> selectDoctor(Integer id);

    /**
     * 查询操作日志的数量
     *
     * @return int
     */
    @Select("select count(*) from operation_log")
    int selectOperLogCount();
}
