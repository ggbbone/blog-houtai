package com.yzg.blog.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class BmsArticle implements Serializable {
    private Integer id;

    @ApiModelProperty(value = "作者id")
    private Integer userId;

    @ApiModelProperty(value = "概要")
    private String outline;

    @ApiModelProperty(value = "所属类别id")
    private Byte categoryId;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "封面图片")
    private String cover;

    @ApiModelProperty(value = "创建时间")
    private Date createdDate;

    @ApiModelProperty(value = "最后修改时间")
    private Date updatedDate;

    @ApiModelProperty(value = "最后评论时间")
    private Date lastCommentTime;

    @ApiModelProperty(value = "点赞次数")
    private Integer likeCount;

    @ApiModelProperty(value = "阅读次数")
    private Integer viewCount;

    @ApiModelProperty(value = "评论数量")
    private Integer commentCount;

    @ApiModelProperty(value = "推荐指数")
    private Integer recommendIndex;

    @ApiModelProperty(value = "状态 1正常")
    private Byte state;

    @ApiModelProperty(value = "是否热门文章")
    private Boolean hot;

    @ApiModelProperty(value = "热门指数")
    private Integer hotIndex;

    @ApiModelProperty(value = "状态（1正常， 2已删除， 3已屏蔽）")
    private Byte status;

    @ApiModelProperty(value = "正文内容")
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

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getRecommendIndex() {
        return recommendIndex;
    }

    public void setRecommendIndex(Integer recommendIndex) {
        this.recommendIndex = recommendIndex;
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
        sb.append(", likeCount=").append(likeCount);
        sb.append(", viewCount=").append(viewCount);
        sb.append(", commentCount=").append(commentCount);
        sb.append(", recommendIndex=").append(recommendIndex);
        sb.append(", state=").append(state);
        sb.append(", hot=").append(hot);
        sb.append(", hotIndex=").append(hotIndex);
        sb.append(", status=").append(status);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}