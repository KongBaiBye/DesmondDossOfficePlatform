package com.yxq.desmonddossofficeplatform.dao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.Map;

/**
 * @Author: yxq
 * @Date: 2023/01/17 15:23
 */
@Mapper
public interface UpdatePersonDao {
    /**
     * 管理员和医生修改个人信息
     * @param map
     * @return
     */
    @Update("update doctor_info set name = #{name},age = #{age},gender = #{gender}," +
            "idCard = #{idCard},phone = #{phone},address = #{address} " +
            "where empno = #{empno}")
    int updatePersonInfo(Map<String,Object> map);

    /**
     * 根据用户名查询个人信息
     * @param username
     * @return
     */
    @Select("select di.name,di.age,di.gender,di.idCard,di.phone," +
            "di.phone,di.empno,di.address,dp.positionName,hd.departmentName from " +
            "doctor_info di left join doctor_position dp " +
            "on di.positionId = dp.id " +
            "left join hospital_department hd " +
            "on di.department = hd.id " +
            "where di.userId = (select id from user_login where username = #{username})")
    Map<String,Object> selectPersonInfo(@Param("username") String username);

    /**
     * 用户修改个人信息
     * @param map
     * @return
     */
    @Update("update user_info set name = #{name},age = #{age},gender = #{gender}," +
            "idCard = #{idCard},phone = #{phone},address = #{address} ,medicareCard=#{medicareCard} " +
            "where userId = #{id}")
    int userUpdatePersonInfo(Map<String,Object> map);

    /**
     * 根据用户名查询个人信息
     * @param username 用户名
     * @return 个人的信息
     */
    @Select("select *from " +
            "user_login ul left join user_info ui " +
            "on ul.id = ui.userId " +
            "where ul.username = #{username}")
    Map<String,Object> userSelectPersonInfo(@Param("username") String username);

    /**
     * 根据用户名查询
     * @param username 用户名
     * @return 密码
     */
    @Select("select password from user_login where username = #{username}")
    String selectPassword(@Param("username") String username);

    /**
     * 修改面
     * @param username
     * @param password
     * @return
     */
    @Update("update user_login set password = #{password} where username = #{username}")
    int changePassword(@Param("username") String username,@Param("password") String password);

}
