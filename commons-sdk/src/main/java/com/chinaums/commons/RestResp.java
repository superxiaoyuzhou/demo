package com.chinaums.commons;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 响应报文实体
 * <p>
 * 创建时间：2017-01-21 13:34
 *
 * @author Sean.Hwang
 */
@Getter
@Setter
public class RestResp<T> implements Serializable {

    private String respCode;

    private String respMsg;

    private T data;
}
