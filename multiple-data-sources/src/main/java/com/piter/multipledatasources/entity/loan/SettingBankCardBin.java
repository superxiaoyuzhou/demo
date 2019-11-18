package com.piter.multipledatasources.entity.loan;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 银行卡bin表
 * </p>
 *
 * @author LX
 * @since 2019-11-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "SETTING_BANK_CARD_BIN", schema = "coop_loan")
@KeySequence("COMMON_SEQ")
public class SettingBankCardBin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID",type = IdType.INPUT)
    private String id;

    /**
     * 银行名称
     */
    @TableField("ISS_BANK_NAME")
    private String issBankName;

    /**
     * 卡类别
     */
    @TableField("BANK_CATEGORY")
    private String bankCategory;

    /**
     * 银行代码
     */
    @TableField("ISS_BANK_CODE")
    private String issBankCode;

    /**
     * 卡名称
     */
    @TableField("CARD_NAME")
    private String cardName;

    /**
     * 卡bin
     */
    @TableField("CARD_BIN")
    private String cardBin;

    /**
     * 卡类型 01-借记卡 02-贷记卡 03-信用卡 04-准贷记卡 05-预付卡 06-虚拟账户
     */
    @TableField("CARD_ATTR")
    private String cardAttr;

    /**
     * 卡号长度
     */
    @TableField("LENGTH")
    private Long length;

    /**
     * 卡bin长度
     */
    @TableField("CARD_BIN_LEN")
    private Long cardBinLen;

    @TableField("CREATE_DATETIME")
    private Date createDatetime;

    @TableField("CREATE_PERSON")
    private String createPerson;

    @TableField("CREATE_TIME")
    private Date createTime;

    @TableField("CREATE_USER_ID")
    private String createUserId;

    @TableField("UPDATE_TIME")
    private Date updateTime;

    @TableField("UPDATE_USER_ID")
    private String updateUserId;


}
