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

/**
 * @author LiXun
 * @date 2019-10-11 14:22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "LOAN_USER_BANK_CARD",schema = "coop_loan")
@EncryptData
public class LoanUserBankCardDispose implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID",type = IdType.INPUT)
    private String id;

    @TableField("CARD_NO")
    @Encrypt
    private String cardNo;
//
//    @TableField("BANK_NAME")
//    private String bankName;

    @TableField("BANK_CODE")
    private String bankCode;

}
