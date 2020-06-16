package com.yzg.blog.dao.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class UmsUserCollection implements Serializable {
    @ApiModelProperty(value = "收藏集id")
    private Integer collectionsId;

    @ApiModelProperty(value = "类型")
    private Byte type;

    @ApiModelProperty(value = "类型id")
    private Integer typeId;

    @ApiModelProperty(value = "状态")
    private Byte status;

    private Date createdDate;

    private Date updatedDate;

    private static final long serialVersionUID = 1L;

    public Integer getCollectionsId() {
        return collectionsId;
    }

    public void setCollectionsId(Integer collectionsId) {
        this.collectionsId = collectionsId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
        sb.append(", collectionsId=").append(collectionsId);
        sb.append(", type=").append(type);
        sb.append(", typeId=").append(typeId);
        sb.append(", status=").append(status);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}