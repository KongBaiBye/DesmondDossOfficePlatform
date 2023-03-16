package com.yxq.desmonddossofficeplatform.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author yxq
 * @Date 2023/2/22 19:45
 */
@Component
public class RedisUtils {
    private static final RedisUtils INSTANCE = new RedisUtils();

    private static final String PREFIX = "nqdj:";

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @PostConstruct
    public void init() {
        INSTANCE.redisTemplate = redisTemplate;
        INSTANCE.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 设置字符串
     *
     * @param key      键
     * @param value    值
     * @param time     时间
     * @param timeUnit 时间单位
     */
    public static void setString(String key, String value, Integer time, TimeUnit timeUnit) {
        INSTANCE.stringRedisTemplate.opsForValue().set(getKey(key), value, time, timeUnit);
    }

    public static void setString(String key, String value, Integer time) {
        setString(key, value, time, TimeUnit.SECONDS);
    }

    public static void setString(String key, String value) {
        INSTANCE.stringRedisTemplate.opsForValue().set(getKey(key), value);
    }

    public static String getString(String key){
        return INSTANCE.stringRedisTemplate.opsForValue().get(getKey(key));
    }

    // 存对象
    public static void set(String key,Object value){
        INSTANCE.redisTemplate.opsForValue().set(getKey(key),value);
    }

    public static void set(String key,Object value,int time){
        INSTANCE.redisTemplate.opsForValue().set(getKey(key),value,time,TimeUnit.SECONDS);
    }

    /**
     * 删除键
     *
     * @param key 关键
     */
    public static void delete(String key){
        INSTANCE.redisTemplate.delete(getKey(key));
    }
    // 取对象
    public static Object get(String key){
        return INSTANCE.redisTemplate.opsForValue().get(getKey(key));
    }

    private static String getKey(String key) {
        return PREFIX + key;
    }


}
