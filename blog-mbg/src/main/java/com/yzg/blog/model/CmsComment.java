package com.yzg.blog.model;

import java.io.Serializable;
import java.util.Date;

public class CmsComment implements Serializable {
    private Integer id;

    private String content;

    private Integer likescount;

    private Integer replycount;

    private Integer userId;

    private Integer respUserId;

    private Integer targetId;

    private Date createdDate;

    private Date updatedDate;

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

    public Integer getLikescount() {
        return likescount;
    }

    public void setLikescount(Integer likescount) {
        this.likescount = likescount;
    }

    public Integer getReplycount() {
        return replycount;
    }

    public void setReplycount(Integer replycount) {
        this.replycount = replycount;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", content=").append(content);
        sb.append(", likescount=").append(likescount);
        sb.append(", replycount=").append(replycount);
        sb.append(", userId=").append(userId);
        sb.append(", respUserId=").append(respUserId);
        sb.append(", targetId=").append(targetId);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}