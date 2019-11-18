package com.piter.multipledatasources.entity.loan;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chinaums.dataencrypt.annotation.EncryptData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author LiXun
 * @date 2019-10-11 16:11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "COMM_BANK_CARD_BIN",schema = "coop_loan")
@EncryptData
public class CommBankCardBin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID",type = IdType.INPUT)
    private String id;

    @TableField("ISS_BANK_NAME")
    private String issBankName;

    @TableField("BANK_CATEGORY")
    private String bankCategory;

    @TableField("ISS_BANK_CODE")
    private String issBankCode;

    @TableField("CARD_NAME")
    private String cardName;

    @TableField("CARD_BIN")
    private String cardBin;

    @TableField("CARD_ATTR")
    private String cardAttr;

    @TableField("LENGTH")
    private Integer length;

    @TableField("CARD_BIN_LEN")
    private Integer cardBinLen;

    @TableField("CREATE_TIME")
    private Date createTime;
}
