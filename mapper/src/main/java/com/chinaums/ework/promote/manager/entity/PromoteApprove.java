package com.chinaums.ework.promote.manager.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "PROMOTE_APPROVE")
public class PromoteApprove {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "PROMOTE_CODE")
    private String promoteCode;

    @Column(name = "BUSI_TYPE")
    private String busiType;

    @Column(name = "BUSI_CODE")
    private String busiCode;

    @Column(name = "BUSI_NAME")
    private String busiName;

    @Column(name = "CHANGE_TYPE")
    private String changeType;

    @Column(name = "CHANGE_CONTENT")
    private String changeContent;

    @Column(name = "APPLY_TIME")
    private Date applyTime;

    @Column(name = "APPLY_USER_NAME")
    private String applyUserName;

    @Column(name = "APPLY_USER_PHONE")
    private String applyUserPhone;

    @Column(name = "APPROVE_STATUS")
    private String approveStatus;

    @Column(name = "APPROVE_BY")
    private String approveBy;

    @Column(name = "APPROVE_TIME")
    private Date approveTime;

    @Column(name = "APPROVE_REMARK")
    private String approveRemark;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    @Column(name = "BUSI_DATA")
    private String busiData;

    /**
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return PROMOTE_CODE
     */
    public String getPromoteCode() {
        return promoteCode;
    }

    /**
     * @param promoteCode
     */
    public void setPromoteCode(String promoteCode) {
        this.promoteCode = promoteCode;
    }

    /**
     * @return BUSI_TYPE
     */
    public String getBusiType() {
        return busiType;
    }

    /**
     * @param busiType
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    /**
     * @return BUSI_CODE
     */
    public String getBusiCode() {
        return busiCode;
    }

    /**
     * @param busiCode
     */
    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    /**
     * @return BUSI_NAME
     */
    public String getBusiName() {
        return busiName;
    }

    /**
     * @param busiName
     */
    public void setBusiName(String busiName) {
        this.busiName = busiName;
    }

    /**
     * @return CHANGE_TYPE
     */
    public String getChangeType() {
        return changeType;
    }

    /**
     * @param changeType
     */
    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    /**
     * @return CHANGE_CONTENT
     */
    public String getChangeContent() {
        return changeContent;
    }

    /**
     * @param changeContent
     */
    public void setChangeContent(String changeContent) {
        this.changeContent = changeContent;
    }

    /**
     * @return APPLY_TIME
     */
    public Date getApplyTime() {
        return applyTime;
    }

    /**
     * @param applyTime
     */
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * @return APPLY_USER_NAME
     */
    public String getApplyUserName() {
        return applyUserName;
    }

    /**
     * @param applyUserName
     */
    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    /**
     * @return APPLY_USER_PHONE
     */
    public String getApplyUserPhone() {
        return applyUserPhone;
    }

    /**
     * @param applyUserPhone
     */
    public void setApplyUserPhone(String applyUserPhone) {
        this.applyUserPhone = applyUserPhone;
    }

    /**
     * @return APPROVE_STATUS
     */
    public String getApproveStatus() {
        return approveStatus;
    }

    /**
     * @param approveStatus
     */
    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    /**
     * @return APPROVE_BY
     */
    public String getApproveBy() {
        return approveBy;
    }

    /**
     * @param approveBy
     */
    public void setApproveBy(String approveBy) {
        this.approveBy = approveBy;
    }

    /**
     * @return APPROVE_TIME
     */
    public Date getApproveTime() {
        return approveTime;
    }

    /**
     * @param approveTime
     */
    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    /**
     * @return APPROVE_REMARK
     */
    public String getApproveRemark() {
        return approveRemark;
    }

    /**
     * @param approveRemark
     */
    public void setApproveRemark(String approveRemark) {
        this.approveRemark = approveRemark;
    }

    /**
     * @return CREATE_TIME
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return UPDATE_TIME
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return BUSI_DATA
     */
    public String getBusiData() {
        return busiData;
    }

    /**
     * @param busiData
     */
    public void setBusiData(String busiData) {
        this.busiData = busiData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", promoteCode=").append(promoteCode);
        sb.append(", busiType=").append(busiType);
        sb.append(", busiCode=").append(busiCode);
        sb.append(", busiName=").append(busiName);
        sb.append(", changeType=").append(changeType);
        sb.append(", changeContent=").append(changeContent);
        sb.append(", applyTime=").append(applyTime);
        sb.append(", applyUserName=").append(applyUserName);
        sb.append(", applyUserPhone=").append(applyUserPhone);
        sb.append(", approveStatus=").append(approveStatus);
        sb.append(", approveBy=").append(approveBy);
        sb.append(", approveTime=").append(approveTime);
        sb.append(", approveRemark=").append(approveRemark);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", busiData=").append(busiData);
        sb.append("]");
        return sb.toString();
    }
}