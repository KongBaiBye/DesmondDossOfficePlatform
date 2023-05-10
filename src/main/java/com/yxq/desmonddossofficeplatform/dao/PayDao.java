package com.yxq.desmonddossofficeplatform.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @Author yxq
 * @Date 2023/4/13 20:03
 */
@Mapper
public interface  PayDao {
    /**
     * 查询门诊日志
     *
     * @param id id
     * @return {@link Map}<{@link String},{@link Object}>
     */
    @Select("select *from outpatient_log where id = #{id}")
    Map<String,Object> selectOutPatientLogs(@Param("id") Integer id);
}
