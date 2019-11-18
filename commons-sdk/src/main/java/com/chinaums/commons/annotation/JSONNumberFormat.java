package com.chinaums.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: lihw
 * @aate: 2018/8/30 10:45
 * @description:
 */

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface JSONNumberFormat {

    /**
     * 格式类型
     * @return
     */
    String format() default "###,###,###,###,##0.00";

    /**
     * 利率类型
     * @return
     */
    RateType rate() default RateType.N;

    /**
     * 利率类型
     * @return
     */
    enum RateType {

        N("无"),
        Q("千分位"),
        B("百分位"),
        W("万分位");

        public String name;

        RateType(String name) {
            this.name = name;
        }


    }
}
