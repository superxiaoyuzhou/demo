package com.piter.multipledatasources.entity.loan;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chinaums.dataencrypt.annotation.Encrypt;
import com.chinaums.dataencrypt.annotation.EncryptData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author LiXun
 * @date 2019-10-11 14:22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "LOAN_USER_BANK_CARD",schema = "coop_loan")
@EncryptData
public class LoanUserBankCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID",type = IdType.INPUT)
    private String id;

    @TableField("USER_NO")
    private String userNo;

    @TableField("PROD_CODE")
    private String prodCode;

    @TableField("CARD_NO")
    @Encrypt
    private String cardNo;

    @TableField("CARD_NO_FUZZY")
    private String cardNoFuzzy;

    @TableField("BANK_NAME")
    private String bankName;

    @TableField("BANK_CODE")
    private String bankCode;

    @TableField("PHONE")
    @Encrypt
    private String phone;

    @TableField("PHONE_FUZZY")
    private String phoneFuzzy;

    @TableField("CARD_ATTR")
    private String cardAttr;

    @TableField("EXPIRE_DATE")
    private String expireDate;

    @TableField("CREATE_TIME")
    private Date createTime;

    @TableField("UPDATE_TIME")
    private Date updateTime;

    @TableField("CARD_NAME")
    @Encrypt
    private String cardName;

    @TableField("CARD_NAME_FUZZY")
    private String cardNameFuzzy;

    @TableField("DEFAULT_FLAG")
    private String defaultFlag;

    @TableField("CREDIT_INFO_ID")
    private String creditInfoId;

    @TableField("DEL_FLAG")
    private String delFlag;
}
