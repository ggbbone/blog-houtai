package com.yzg.blog.dao.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class BmsArticle implements Serializable {
    private Integer id;

    @ApiModelProperty(value = "作者id")
    private Integer userId;

    @ApiModelProperty(value = "概要")
    private String content;

    @ApiModelProperty(value = "所属类别id")
    private Integer categoryId;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "是否有封面图片")
    private Boolean cover;

    @ApiModelProperty(value = "封面图片url")
    private String coveUrl;

    @ApiModelProperty(value = "创建时间")
    private Date createdDate;

    @ApiModelProperty(value = "最后修改时间")
    private Date updatedDate;

    @ApiModelProperty(value = "最后评论时间")
    private Date lastCommentTime;

    @ApiModelProperty(value = "阅读次数")
    private Integer viewCount;

    @ApiModelProperty(value = "点赞次数")
    private Integer likeCount;

    @ApiModelProperty(value = "评论数量")
    private Integer commentCount;

    @ApiModelProperty(value = "推荐指数")
    private Integer recommendIndex;

    @ApiModelProperty(value = "是否热门文章")
    private Boolean hot;

    @ApiModelProperty(value = "热门指数")
    private Integer hotIndex;

    @ApiModelProperty(value = "状态（1正常， 2已删除， 3已屏蔽）")
    private Byte status;

    @ApiModelProperty(value = "正文内容html")
    private String html;

    @ApiModelProperty(value = "正文内容markdown")
    private String markdown;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Boolean getCover() {
        return cover;
    }

    public void setCover(Boolean cover) {
        this.cover = cover;
    }

    public String getCoveUrl() {
        return coveUrl;
    }

    public void setCoveUrl(String coveUrl) {
        this.coveUrl = coveUrl == null ? null : coveUrl.trim();
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

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
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

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html == null ? null : html.trim();
    }

    public String getMarkdown() {
        return markdown;
    }

    public void setMarkdown(String markdown) {
        this.markdown = markdown == null ? null : markdown.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", content=").append(content);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", title=").append(title);
        sb.append(", cover=").append(cover);
        sb.append(", coveUrl=").append(coveUrl);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append(", lastCommentTime=").append(lastCommentTime);
        sb.append(", viewCount=").append(viewCount);
        sb.append(", likeCount=").append(likeCount);
        sb.append(", commentCount=").append(commentCount);
        sb.append(", recommendIndex=").append(recommendIndex);
        sb.append(", hot=").append(hot);
        sb.append(", hotIndex=").append(hotIndex);
        sb.append(", status=").append(status);
        sb.append(", html=").append(html);
        sb.append(", markdown=").append(markdown);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}