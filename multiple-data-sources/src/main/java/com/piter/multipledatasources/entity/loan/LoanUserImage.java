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
 * @date 2019-10-11 14:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "LOAN_USER_IMAGE",schema = "coop_loan")
@EncryptData
public class LoanUserImage implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "ID",type = IdType.INPUT)
    private String id;

    @TableField("USER_NO")
    private String userNo;

    @TableField("PROD_CODE")
    private String prodCode;

    @TableField("IMAGE_TYPE")
    private String imageType;

    @TableField("FILE_NAME")
    private String fileName;

    @TableField("FILE_ID")
    private String fileId;

    @TableField("FILE_PATH")
    private String filePath;

    @TableField("CREATE_TIME")
    private Date createTime;

    @TableField("UPDATE_TIME")
    private Date updateTime;

    @TableField("COOP_FILE_PATH")
    private String coopFilePath;

    @TableField("COOP_FILE_NAME")
    private String coopFileName;

    @TableField("CREDIT_INFO_ID")
    private String creditInfoId;
}
