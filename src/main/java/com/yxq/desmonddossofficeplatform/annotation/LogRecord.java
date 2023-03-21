package com.yxq.desmonddossofficeplatform.annotation;

import java.lang.annotation.*;

/**
 * 自定义日志记录注解
 *
 * @Author yxq
 * @Date 2023/3/21 17:18
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogRecord {
}
