package com.chinaums.commons;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * 基础model
 *
 * @author xionglei
 * @create 2018-08-22 13:46
 */

public class BaseModel implements Serializable {

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
