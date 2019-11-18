package com.chinaums.commons.extend.poi;

import com.chinaums.commons.extend.poi.converter.FieldConverter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xionglei
 * @create 2018-09-15 20:30
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExportConfig {

    /**
     * 列表头名称
     * @return
     */
    String value() default "field";

    /**
     * 列顺序，越小越靠前
     * @return
     */
    int index() default 99999;

    /**
     * 列宽度
     * @return
     */
    short width() default 300;

    /**
     * 是否导出
     * @return
     */
    boolean isExportData() default true;

    /**
     * 日期格式化
     * @return
     */
    String format() default "";

    /**
     * 枚举class类型
     */
    Class<?> enumClass() default Void.class;

    /**
     * 转码的方法名，需要在枚举中定义，方法必须满足下列要求：1.入参有且只有一个，且类型为String 2.返回类型为String
     */
    String methodName() default "";

    /**
     * 字段值转换器
     * @return
     */
    Class<? extends FieldConverter> converter() default FieldConverter.class;

    /**
     * 导出格式
     * @return
     */
    ExportTypeEnum exportType() default ExportTypeEnum.STR;
}
