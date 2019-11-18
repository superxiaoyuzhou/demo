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
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 贷款用户授信信息表
 * </p>
 *
 * @author Piter
 * @since 2019-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "LOAN_USER_CREDIT_INFO",schema = "coop_loan")
@EncryptData
public class LoanUserCreditInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID",type = IdType.INPUT)
    private String id;

    /**
     * 用户号
     */
    @TableField("USER_NO")
    private String userNo;

    /**
     * 产品编号
     */
    @TableField("PROD_CODE")
    private String prodCode;

    /**
     * 用户姓名
     */
    @TableField("REAL_NAME")
    @Encrypt
    private String realName;

    @TableField("REAL_NAME_FUZZY")
    private String realNameFuzzy;

    /**
     * 证件类型
     */
    @TableField("CERT_TYPE")
    private String certType;

    /**
     * 证件号码
     */
    @Encrypt
    @TableField("CERT_ID")
    private String certId;

    /**
     * 证件号码-脱敏
     */
    @TableField("CERT_ID_FUZZY")
    private String certIdFuzzy;

    /**
     * 手机号
     */
    @Encrypt
    @TableField("PHONE")
    private String phone;

    /**
     * 手机号-脱敏
     */
    @TableField("PHONE_FUZZY")
    private String phoneFuzzy;

    /**
     * 身份证有效期起
     */
    @TableField("CERT_VDATE_START")
    private String certVdateStart;

    /**
     * 身份证有效期止
     */
    @TableField("CERT_VDATE_END")
    private String certVdateEnd;

    /**
     * 身份证签发机关
     */
    @TableField("CERT_ISSU_ORG")
    private String certIssuOrg;

    /**
     * 实名银行账号
     */
    @Encrypt
    @TableField("BANK_ACCT_NO")
    private String bankAcctNo;

    /**
     * 实名银行账号(脱敏)
     */
    @TableField("BANK_ACCT_NO_FUZZY")
    private String bankAcctNoFuzzy;

    /**
     * 邮箱
     */
    @TableField("EMAIL")
    private String email;

    /**
     * X-未知的性别；
     1-男；
     2-女
     */
    @TableField("SEX")
    private String sex;

    /**
     * 民族
     */
    @TableField("NATIONAL")
    private String national;

    /**
     * 户籍地址
     */
    @TableField("REG_ADDRESS")
    private String regAddress;

    /**
     * 居住地址
     */
    @TableField("RESID_ADDRESS")
    private String residAddress;

    /**
     * 省编码
     */
    @TableField("PROVINCE_CODE")
    private String provinceCode;

    /**
     * 省名称
     */
    @TableField("PROVINCE_NAME")
    private String provinceName;

    /**
     * 市编码
     */
    @TableField("CITY_CODE")
    private String cityCode;

    /**
     * 市名称
     */
    @TableField("CITY_NAME")
    private String cityName;

    /**
     * 区编码
     */
    @TableField("ZONE_CODE")
    private String zoneCode;

    /**
     * 区名称
     */
    @TableField("ZONE_NAME")
    private String zoneName;

    /**
     * 学历
     */
    @TableField("EDUCATION")
    private String education;

    /**
     * 工作年限
     */
    @TableField("WORK_YEAR")
    private BigDecimal workYear;

    /**
     * 联系人关系
     */
    @TableField("CONTACT_RELATION")
    private String contactRelation;

    /**
     * 联系人姓名
     */
    @TableField("CONTACT_NAME")
    private String contactName;

    /**
     * 联系人手机号
     */
    @TableField("CONTACT_MOBILE")
    private String contactMobile;

    /**
     * 扩展数据(json)
     */
    @TableField("EXT_DATA")
    private String extData;

    /**
     * 渠道用户编号
     */
    @TableField("CHANNEL_USER_NO")
    private String channelUserNo;

    /**
     * 渠道申请单号
     */
    @TableField("CHANNEL_APPLY_NO")
    private String channelApplyNo;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private Date updateTime;

    /**
     * 申请成功时间
     */
    @TableField("APPLY_SUCC_TIME")
    private Date applySuccTime;

    /**
     * 状态字段：Y-正常,N-废弃
     */
    @TableField("STATUS")
    private String status;

    /**
     * 是否首次借款：Y首次，N非首次
     */
    @TableField("FIRST_LOAN")
    private String firstLoan;

    /**
     * 默认银行卡信息ID
     */
    @TableField("BANK_CARD_ID")
    private String bankCardId;

    /**
     * 贷款用途,字典LOAN_PURPOSE
     */
    @TableField("LOAN_PURPOSE")
    private String loanPurpose;

    /**
     * 婚姻状况，字典MARRIAGE
     */
    @TableField("MARRIAGE")
    private String marriage;

    /**
     * 单位名称
     */
    @TableField("COMPANY_NAME")
    private String companyName;

    /**
     * 单位电话(区号)
     */
    @TableField("COMPANY_TEL_AREA")
    private String companyTelArea;

    /**
     * 单位电话
     */
    @TableField("COMPANY_TEL_NO")
    private String companyTelNo;

    /**
     * 单位电话(分机号)
     */
    @TableField("COMPANY_TEL_EXT")
    private String companyTelExt;

    /**
     * 银杏分ID
     */
    @TableField("YXF_ID")
    private String yxfId;

    /**
     * 单位地址
     */
    @TableField("COMPANY_DETAIL_ADDRESS")
    private String companyDetailAddress;

    /**
     * 申请金额
     */
    @TableField("APPLY_AMOUNT")
    private BigDecimal applyAmount;

    /**
     * 申请期数
     */
    @TableField("LOAN_PERIOD")
    private Long loanPeriod;

}
