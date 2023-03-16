package com.yxq.desmonddossofficeplatform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: yxq
 * @Date: 2023/01/16 10:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private Boolean gender;
    private String idCard;
    private String phone;
    private String address;
    private String medicareCard;
    private String userId;
}
