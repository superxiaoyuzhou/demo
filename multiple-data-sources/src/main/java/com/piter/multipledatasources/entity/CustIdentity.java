package com.piter.multipledatasources.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
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
 * <p>
 * 身份认证
 * </p>
 *
 * @author Piter
 * @since 2019-07-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("CUST_IDENTITY")
@EncryptData
public class CustIdentity implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * ID
   */
  @TableId(value = "ID", type = IdType.INPUT)
  private Long id;

  /**
   * 客户编码
   */
  @TableField(value = "CUST_NO",updateStrategy = FieldStrategy.NOT_EMPTY)
  private String custNo;

  /**
   * 姓名
   */
  @Encrypt
  @TableField(value = "NAME",updateStrategy = FieldStrategy.NOT_EMPTY)
  private String name;

  /**
   * 身份证号码
   */
  @Encrypt
  @TableField(value = "ID_CARD",updateStrategy = FieldStrategy.NOT_EMPTY)
  private String idCard;

  /**
   * 身份证号码脱敏
   */
  @TableField(value = "ID_CARD_FUZZY",updateStrategy = FieldStrategy.NOT_EMPTY)
  private String idCardFuzzy;

  /**
   * 签发机关
   */
  @TableField(value = "ISSU_ORG")
  private String issuOrg;

  /**
   * 有效期限开始
   */
  @TableField(value = "VALID_BEGIN")
  private String validBegin;

  /**
   * 有效期限结束
   */
  @TableField(value = "VALID_END")
  private String validEnd;

  /**
   * 性别:M男，F女
   */
  @TableField(value = "SEX")
  private String sex;

  /**
   * 出生日期
   */
  @TableField(value = "BIRTHDAY")
  private String birthday;

  /**
   * 民族
   */
  @TableField(value = "NATION")
  private String nation;

  /**
   * 住址
   */
  @TableField(value = "ADDRESS")
  private String address;

  /**
   * 正面fileId
   */
  @TableField(value = "FRONT_FILE_ID")
  private String frontFileId;

  /**
   * 反面fileId
   */
  @TableField(value = "BACK_FILE_ID")
  private String backFileId;

  /**
   * 创建人
   */
  @TableField("CREATE_BY")
  private String createBy;

  /**
   * 创建时间
   */
  @TableField("CREATE_TIME")
  private Date createTime;

  /**
   * 更新人
   */
  @TableField("UPDATE_BY")
  private String updateBy;

  /**
   * 更新时间
   */
  @TableField("UPDATE_TIME")
  private Date updateTime;

  /**
   * 姓名脱敏
   */
  @TableField(value = "NAME_FUZZY",updateStrategy = FieldStrategy.NOT_EMPTY)
  private String nameFuzzy;

}
