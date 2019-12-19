package com.yzg.blog.model;

import java.io.Serializable;
import java.util.Date;

public class UmsUserInfo implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private String avatar;

    private String blogAddress;

    private Integer collectedEntriesCount;

    private Integer collectionsCount;

    private Date createdDate;

    private Date updatedDate;

    private String email;

    private Integer followeesCount;

    private Integer followersCount;

    private String outline;

    private Date lastLoginDate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Integer getCollectionsCount() {
        return collectionsCount;
    }

    public void setCollectionsCount(Integer collectionsCount) {
        this.collectionsCount = collectionsCount;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getFolloweesCount() {
        return followeesCount;
    }

    public void setFolloweesCount(Integer followeesCount) {
        this.followeesCount = followeesCount;
    }

    public Integer getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", avatar=").append(avatar);
        sb.append(", blogAddress=").append(blogAddress);
        sb.append(", collectedEntriesCount=").append(collectedEntriesCount);
        sb.append(", collectionsCount=").append(collectionsCount);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append(", email=").append(email);
        sb.append(", followeesCount=").append(followeesCount);
        sb.append(", followersCount=").append(followersCount);
        sb.append(", outline=").append(outline);
        sb.append(", lastLoginDate=").append(lastLoginDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}