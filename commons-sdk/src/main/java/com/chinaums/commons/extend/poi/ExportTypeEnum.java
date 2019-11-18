package com.chinaums.commons.extend.poi;

/**
 * 导出格式枚举
 *
 * @author XL
 * @create 2019-04-25 18:27
 */

public enum  ExportTypeEnum {
    STR("STR", "字符串"),
    NUM("NUM", "数字"),
    AMT("AMT", "金额"),
    PERMILLAGE("PERMILLAGE", "千分比"),
    PERCENT("PERCENT", "百分比");

    public final String code;

    public final String message;

    ExportTypeEnum(String code, String message){
        this.code = code;
        this.message = message;
    }
}
