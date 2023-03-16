package com.yxq.desmonddossofficeplatform.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: yxq
 * @Date: 2023/01/20 16:05
 */
@Mapper
public interface UserDao {
    /**
     * 用户查询自己的健康信息
     * @param username
     * @return
     */
    @Select("select ui.name,ui.idCard,ui.medicareCard,hi.height,hi.examineDate," +
            "hi.pulse,hi.heartbeat,hi.bloodPressure,hi.bloodOxygen,hi.bloodGlucose,hi.weight,hi.examineDate from " +
            "user_login ul left join user_info ui on ul.id = ui.userId " +
            "left join health_info hi on ui.medicareCard = hi.medicareCardId " +
            "where ul.username = #{username}")
    List<Map<String,Object>> selectUserPersonHealthInfo(@Param("username") String username);


    /**
     * 查询个人就诊历史
     * @param username
     * @return
     */
    @Select("select ui.name,ui.idCard,mh.symptom,mh.creatTime from " +
            "user_login ul left join user_info ui on ul.id = ui.userId " +
            "left join medical_history mh on ui.medicareCard = mh.medicareCardId " +
            "where ul.username = #{username}")
    List<Map<String,Object>> selectUserPersonHealthHistory(@Param("username") String username);

    /**
     * 查询家人健康信息
     * @param name
     * @param idCard
     * @return
     */
    @Select("select *from" +
            " user_info ui left join health_info hi " +
            "on ui.medicareCard = hi.medicareCardId " +
            "where ui.name = #{name} and ui.idCard = #{idCard}")
    List<Map<String,Object>> selectFamilyHealthInfo(@Param("name") String name,@Param("idCard") String idCard);

    /**
     * 查询个人就诊历史
     * @param name
     * @param idCard
     * @return
     */
    @Select("select ui.name,ui.idCard,mh.symptom,mh.creatTime " +
            "from user_info ui left join medical_history mh on ui.medicareCard = mh.medicareCardId " +
            "where ui.name = #{name} and ui.idCard = #{idCard}")
    List<Map<String,Object>> selectFamilyHealthHistory(@Param("name") String name,@Param("idCard") String idCard);

    /**
     * 用户注册
     * @param map
     * @return
     */
    @Insert("insert into user_login(username, password, roleId)" +
            "VALUES (#{username},#{password},2)")
    int insertUserLogin(Map<String,Object> map);

    /**
     * 查找id
     * @param username
     * @return
     */
    @Select("select *from user_login where username=#{username}")
    Map<String,Object> selectId(@Param("username") Object username);

    /**
     * 插入userId
     * @param id
     * @return
     */
    @Insert("insert into user_info(userId) value (#{id})")
    int insertUserId(@Param("id")int id);
}
