package com.yzg.blog.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class UmsUserFollow implements Serializable {
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "被关注的对象id")
    private Integer followId;

    @ApiModelProperty(value = "关注的类型（1：用户， 2：标签）")
    private Byte type;

    @ApiModelProperty(value = "状态（1：关注中， 0取消关注）")
    private Boolean status;

    @ApiModelProperty(value = "关注时间")
    private Date createdDate;

    private static final long serialVersionUID = 1L;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFollowId() {
        return followId;
    }

    public void setFollowId(Integer followId) {
        this.followId = followId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", followId=").append(followId);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}