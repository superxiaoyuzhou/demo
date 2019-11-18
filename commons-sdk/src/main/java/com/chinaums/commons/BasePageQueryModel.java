package com.chinaums.commons;

import lombok.Getter;
import lombok.Setter;

/**
 * 基础查询model
 *
 * @author xionglei
 * @create 2018-08-22 17:36
 */
@Getter
@Setter
public class BasePageQueryModel extends BaseModel {

    /**页记录数*/
    private Integer pageSize = 20;

    /**页码(从1开始)*/
    private Integer pageNo = 1;

    @Override
    public String toString() {
        return super.toString();
    }
}
