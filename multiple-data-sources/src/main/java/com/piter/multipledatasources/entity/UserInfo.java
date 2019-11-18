package com.piter.multipledatasources.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author: lihw
 * @aate: 2018/8/24 09:16
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "USER_INFO")
public class UserInfo {

    @TableId(value = "ID",type = IdType.INPUT)
    private String id;

    @TableField(value = "USER_NO")
    private String userNo;

    @TableField(value = "PHONE")
    private String phone;

    @TableField(value = "USER_STATUS")
    private String userStatus;

    @TableField(value = "RECOMMEND")
    private String recommend;

    @TableField(value = "CREATE_TIME")
    private Date createTime;

    @TableField(value = "UPDATE_TIME")
    private Date updateTime;

    @TableField(value = "CUST_NO")
    private String custNo;
}