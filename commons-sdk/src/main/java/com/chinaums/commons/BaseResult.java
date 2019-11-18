package com.chinaums.commons;

/**
 * base 结果
 * @author xionglei
 * @create 2018-08-20 18:33
 */

public interface BaseResult {

    /**
     * 成功响应码
     */
    String SUCCESS_RESP_CODE = "0000";

    /**
     * 成功响应信息
     */
    String SUCCESS_RESP_MSG = "成功";

    /**
     * 获取响应码
     * @return
     */
    String getRespCode();

    /**
     * 获取响应信息
     * @return
     */
    String getRespMsg();
}
