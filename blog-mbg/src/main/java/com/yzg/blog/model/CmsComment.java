package com.yzg.blog.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class CmsComment implements Serializable {
    private Integer id;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "点赞人数")
    private Integer likesCount;

    @ApiModelProperty(value = "回复数量")
    private Integer replyCount;

    @ApiModelProperty(value = "发表用户")
    private Integer userId;

    @ApiModelProperty(value = "接收用户")
    private Integer respUserId;

    @ApiModelProperty(value = "父评论id，为0时表示没有父评论")
    private Integer targetId;

    @ApiModelProperty(value = "创建时间")
    private Date createdDate;

    @ApiModelProperty(value = "最后更新时间")
    private Date updatedDate;

    @ApiModelProperty(value = "状态 （1：正常， 2已删除， 3已屏蔽）")
    private Byte status;

    @ApiModelProperty(value = "评论目标id")
    private Integer parentId;

    @ApiModelProperty(value = "评论类型 (1:文章， 2讨论 )")
    private Byte parentType;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRespUserId() {
        return respUserId;
    }

    public void setRespUserId(Integer respUserId) {
        this.respUserId = respUserId;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Byte getParentType() {
        return parentType;
    }

    public void setParentType(Byte parentType) {
        this.parentType = parentType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", content=").append(content);
        sb.append(", likesCount=").append(likesCount);
        sb.append(", replyCount=").append(replyCount);
        sb.append(", userId=").append(userId);
        sb.append(", respUserId=").append(respUserId);
        sb.append(", targetId=").append(targetId);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append(", status=").append(status);
        sb.append(", parentId=").append(parentId);
        sb.append(", parentType=").append(parentType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}