package com.chinaums.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 忽略日志打印
 *
 * @author xionglei
 * @create 2018-06-28 8:54
 */
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RUNTIME)
public @interface IgnoreLog {

    /**
     * 是否忽略请求日志打印  true:是 false:否
     */
    boolean ignoreRequest() default true;

    /**
     * 是否忽略响应日志打印  true:是 false:否
     */
    boolean ignoreResponse() default false;
}
