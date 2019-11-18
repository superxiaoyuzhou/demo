package com.chinaums.commons.model;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @auther xionglei
 * @create 2018-01-25 9:36
 */

@Data
public class Page<T> implements Serializable {

    /**总记录数*/
    private long totalElements;

    /**页面内容*/
    private List<T> content;

    public Page(){
        super();
    }

    public Page(long totalElements, List<T> content) {
        this.totalElements = totalElements;
        this.content = content;
    }

    public Page(PageInfo pageInfo) {
        this.totalElements = pageInfo.getTotal();
        this.content = pageInfo.getList();
    }
}
