package com.chinaums.commons.enums;

import com.chinaums.commons.BaseResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通用响应码
 *
 * @auther xionglei
 * @create 2018-02-22 14:13
 */

@Getter
@AllArgsConstructor
public enum CommRespCode implements BaseResult {
    SUCCESS("0000","操作成功"),
    SYSTEM_ERROR("9999","系统繁忙,请稍后再试"),
    SESSION_TIME_OUT("9998","用户未登录或会话超时,请重新登录"),
    PARAM_ERROR("2001","请求参数错误：{0}"),
    NETWORK_ERROR("2002","网络异常"),
    INNER_NETWORK_ERROR("2003","内部服务网络异常"),
    DB_ERROR("2004","数据库访问错误"),
    OPERATE_ERROR("2005","操作失败：{0}"),
    REQ_REPEAT("2006","禁止重复提交"),
    TRANSFORM_READ_ERROR("2007","报文读异常"),
    TRANSFORM_WRITE_ERROR("2008","报文写异常"),
    HTTP_STATUS_ERROR("2009", "HTTP请求失败"),
    USER_AUTH_ERROR("2010","用户未授权"),
    SERVICE_NOT_CONFIG("2011","服务[{0}]未配置"),
    ;

    private String respCode;

    private String respMsg;

}
