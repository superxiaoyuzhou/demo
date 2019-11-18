package com.chinaums.commons.utils;


import com.chinaums.commons.BaseResult;
import com.chinaums.commons.RestResp;

/**
 * 结果处理工具类
 *
 * @author xionglei
 * @create 2018-08-22 13:46
 */
public class RestRespUtil {

    /**
     * 响应成功
     * @return
     */
    public static RestResp success() {
        return success(null);
    }

    /**
     * 响应成功
     * @param data
     * @param <T>
     * @return
     */
    public static <T> RestResp<T> success(T data) {
        return success(data, BaseResult.SUCCESS_RESP_CODE, BaseResult.SUCCESS_RESP_MSG);
    }

    /**
     * 响应成功
     * @param data
     * @param respMsg
     * @param <T>
     * @return
     */
    public static <T> RestResp<T> success(T data, String respMsg) {
        return success(data, BaseResult.SUCCESS_RESP_CODE, respMsg);
    }

    /**
     * 响应成功
     * @param data
     * @param respCode
     * @param respMsg
     * @param <T>
     * @return
     */
    public static <T> RestResp<T> success(T data, String respCode, String respMsg) {
        RestResp<T> restResp = new RestResp<>();
        restResp.setRespCode(respCode);
        restResp.setRespMsg(respMsg);
        restResp.setData(data);
        return restResp;
    }

    /**
     * 响应失败
     * @param respCode
     * @param respMsg
     * @return
     */
    public static RestResp error(String respCode, String respMsg) {
        RestResp restResp = new RestResp();
        restResp.setRespCode(respCode);
        restResp.setRespMsg(respMsg);
        return restResp;
    }

    /**
     * 响应结果
     * @param baseResult
     * @return
     */
    public static RestResp result(BaseResult baseResult) {
        RestResp restResp = new RestResp();
        restResp.setRespCode(baseResult.getRespCode());
        restResp.setRespMsg(baseResult.getRespMsg());
        return restResp;
    }
}
