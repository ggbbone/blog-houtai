package com.yzg.blog.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class UmsUserInfoOther implements Serializable {
    private Integer id;

    @ApiModelProperty(value = "头像地址")
    private String avatar;

    @ApiModelProperty(value = "个人主页")
    private String blogAddress;

    @ApiModelProperty(value = "关注的收藏集数量")
    private Integer collectedEntriesCount;

    @ApiModelProperty(value = "创建的收藏集数量")
    private Integer collectionCount;

    private Date createdDate;

    private Date updatedDate;

    @ApiModelProperty(value = "他关注的人数")
    private Integer followeeCount;

    @ApiModelProperty(value = "关注他的人数")
    private Integer followerCount;

    @ApiModelProperty(value = "个人简介")
    private String outline;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBlogAddress() {
        return blogAddress;
    }

    public void setBlogAddress(String blogAddress) {
        this.blogAddress = blogAddress;
    }

    public Integer getCollectedEntriesCount() {
        return collectedEntriesCount;
    }

    public void setCollectedEntriesCount(Integer collectedEntriesCount) {
        this.collectedEntriesCount = collectedEntriesCount;
    }

    public Integer getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(Integer collectionCount) {
        this.collectionCount = collectionCount;
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

    public Integer getFolloweeCount() {
        return followeeCount;
    }

    public void setFolloweeCount(Integer followeeCount) {
        this.followeeCount = followeeCount;
    }

    public Integer getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", avatar=").append(avatar);
        sb.append(", blogAddress=").append(blogAddress);
        sb.append(", collectedEntriesCount=").append(collectedEntriesCount);
        sb.append(", collectionCount=").append(collectionCount);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append(", followeeCount=").append(followeeCount);
        sb.append(", followerCount=").append(followerCount);
        sb.append(", outline=").append(outline);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}