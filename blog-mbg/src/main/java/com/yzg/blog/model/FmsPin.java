package com.yzg.blog.model;

import java.io.Serializable;
import java.util.Date;

public class FmsPin implements Serializable {
    private Integer id;

    private Date createdDate;

    private Date updatedDate;

    private Integer likescount;

    private Integer commentscount;

    private Integer userId;

    private String content;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getLikescount() {
        return likescount;
    }

    public void setLikescount(Integer likescount) {
        this.likescount = likescount;
    }

    public Integer getCommentscount() {
        return commentscount;
    }

    public void setCommentscount(Integer commentscount) {
        this.commentscount = commentscount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append(", likescount=").append(likescount);
        sb.append(", commentscount=").append(commentscount);
        sb.append(", userId=").append(userId);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}