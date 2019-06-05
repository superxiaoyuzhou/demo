package com.piter.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * 广告Banner表
 * </p>
 *
 * @author lyq
 * @since 2019-05-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("BANNER")
public class Banner implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "ID",type = IdType.INPUT)
    private String id;

    /**
     * 推广方编码
     */
    @TableField("PROMOTE_CODE")
    private String promoteCode;

    /**
     * banner编码
     */
    @TableField("BANNER_CODE")
    private String bannerCode;

    /**
     * banner名称
     */
    @TableField("BANNER_NAME")
    private String bannerName;

    /**
     * banner文件ID
     */
    @TableField(value = "BANNER_FILE_ID", fill = FieldFill.UPDATE)
    private String bannerFileId;

    /**
     * banner图片路径
     */
    @TableField(value = "BANNER_FILE_PATH", fill = FieldFill.UPDATE)
    private String bannerFilePath;

    /**
     * banner链接
     */
    @TableField(value = "BANNER_LINK_URL", fill = FieldFill.UPDATE)
    private String bannerLinkUrl;

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
     * 状态 Y-启用
N-停用
     */
    @TableField("STATUS")
    private String status;


}
