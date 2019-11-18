package com.piter.multipledatasources.entity.ccs;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
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
 * <p>
 * 银行卡
 * </p>
 *
 * @author Piter
 * @since 2019-07-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "CUST_BANK_CARD" ,schema = "ccs")
@KeySequence("COMMON_SEQ")
@EncryptData
public class CustBankCardRepair implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "ID",type = IdType.INPUT)
    private Long id;

    /**
     * 卡号
     */
    @Encrypt
    @TableField(value = "CARD_NO",updateStrategy = FieldStrategy.NOT_EMPTY)
    private String cardNo;

    /**
     * 银行编码
     */
    @TableField("BANK_CODE")
    private String bankCode;
//
//    /**
//     * 银行名称
//     */
//    @TableField("BANK_NAME")
//    private String bankName;

//    /**
//     * 更新时间
//     */
//    @TableField("UPDATE_TIME")
//    private Date updateTime;

}
