package com.chinaums.commons.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * Bean工具类
 *
 * @author xionglei
 * @create 2018-08-14 18:51
 */

public class BeanUtils {

    /**
     * bean to bean
     * @param s
     * @param dClass
     * @param <S>
     * @param <D>
     * @return
     */
    public static <S,D> D beanToBean(S s, Class<D> dClass){
        return JSON.parseObject(JSON.toJSONString(s), dClass);
    }

    /**
     * bean to map
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Map beanToMap(T t){
        return JSON.parseObject(JSON.toJSONString(t), new TypeReference<Map>() {});
    }

    /**
     * map to bean
     * @param map
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T mapToBean(Map map, Class<T> tClass){
        return JSON.parseObject(JSON.toJSONString(map), tClass);
    }

    /**
     * bean copy
     * @param d 目标
     * @param s 源
     * @param <S>
     * @param <D>
     */
    public static <D, S> void copy(D d, S s){
        try {
            org.springframework.beans.BeanUtils.copyProperties(s,d);
        } catch (Exception e) {
            throw new RuntimeException("bean copy error", e);
        }
    }
}
