package com.yxq.desmonddossofficeplatform.dao;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @Author: yxq
 * @Date: 2023/01/14 20:29
 */
@Mapper
public interface LoginDao {
    /**
     *登录操作
     * @param username
     * @return
     */
    @Select("select  ur.roleName ,ul.password  from user_login ul left join user_role ur on ul.roleId = ur.id where username = #{username}")
    Map<String,Object> selectPassword(String username);
    /**
     * 登录日志
     * @param map
     */
    @Insert("insert into login_log(loginName, success, msg, loginTime, username) " +
            "VALUES ('登录',#{success},#{msg},now(),#{username})")
    void insertLoginLog(Map<String,Object> map);

    /**
     * 退出记录日志
     * @param username
     * @return
     */
    @Insert("insert into login_log(loginName, success, msg, loginTime, username) " +
            "VALUES ('退出',1,'',now(),#{username})")
    int insertLoginOutLog(String username);

    /**
     * 添加操作日志
     * @param map
     * @return
     */
    @Insert("insert into operation_log(logName, success, operationCrew, createTime)" +
            " VALUES (#{logName},#{success},#{operationCrew},now())")
    int insertOperationLog(Map<String,Object> map);

    /**
     *查询用户姓名
     * @param id
     * @return
     */
    @Select("select name from user_info where id=#{id}")
    Map<String,Object> selectUserName(@Param("id") int id);

    /**
     *
     * @param empno
     * @return
     */
    @Select("select name from doctor_info where empno = #{empno}")
    Map<String,Object> selectDoctorName(@Param("empno")String empno);

    /**
     * 药品日志记录
     * @param map
     * @return
     */
    @Insert("insert into drug_log(logName, success, createTime, operationCrew)" +
            " VALUES (#{logName},#{success},now(),#{operationCrew})")
    int insertDrugLog(Map<String,Object> map);

}
