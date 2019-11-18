package com.chinaums.commons.utils;

/**
 * @Description : json字符串工具类
 * @Author : yuanxh
 * @CreateDate : 2018/11/26
 */
public class JsonStringUtil {
    /**
     * 双引号
     */
    private static final String STRING_DOUBLE_QUOTE = "\"\"";
    /**
     * 大括号
     */
    private static final String STRING_BRACE= "{}";

    /**
     *  判断json串是否为空（双引号和大括号都判断为空）
     * @param jsonString
     * @return true-empty
     */
    public static boolean isEmpty(String jsonString){
        return (jsonString == null || "".equals(jsonString)
                || STRING_DOUBLE_QUOTE.equals(jsonString)
                || STRING_BRACE.equals(jsonString));
    }
}
