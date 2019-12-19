package com.yzg.blog.model;

import java.io.Serializable;
import java.util.Date;

public class BmsArticle implements Serializable {
    private Integer id;

    private Integer userId;

    private String outline;

    private Byte categoryId;

    private String title;

    private String cover;

    private Date createdDate;

    private Date updatedDate;

    private Date lastCommentTime;

    private Integer likesCount;

    private Integer viewsCount;

    private Integer commentsCount;

    private Integer weightIndex;

    private Byte state;

    private Boolean hot;

    private Integer hotIndex;

    private String content;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public Byte getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Byte categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
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

    public Date getLastCommentTime() {
        return lastCommentTime;
    }

    public void setLastCommentTime(Date lastCommentTime) {
        this.lastCommentTime = lastCommentTime;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public Integer getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(Integer viewsCount) {
        this.viewsCount = viewsCount;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public Integer getWeightIndex() {
        return weightIndex;
    }

    public void setWeightIndex(Integer weightIndex) {
        this.weightIndex = weightIndex;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Boolean getHot() {
        return hot;
    }

    public void setHot(Boolean hot) {
        this.hot = hot;
    }

    public Integer getHotIndex() {
        return hotIndex;
    }

    public void setHotIndex(Integer hotIndex) {
        this.hotIndex = hotIndex;
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
        sb.append(", userId=").append(userId);
        sb.append(", outline=").append(outline);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", title=").append(title);
        sb.append(", cover=").append(cover);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append(", lastCommentTime=").append(lastCommentTime);
        sb.append(", likesCount=").append(likesCount);
        sb.append(", viewsCount=").append(viewsCount);
        sb.append(", commentsCount=").append(commentsCount);
        sb.append(", weightIndex=").append(weightIndex);
        sb.append(", state=").append(state);
        sb.append(", hot=").append(hot);
        sb.append(", hotIndex=").append(hotIndex);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}