package com.yzg.blog.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class CmsReport implements Serializable {
    private Integer id;

    @ApiModelProperty(value = "发起用户id")
    private Integer userId;

    @ApiModelProperty(value = "举报类型")
    private Byte type;

    @ApiModelProperty(value = "举报对象类型")
    private Byte respTpye;

    @ApiModelProperty(value = "对象id")
    private Integer respTypeId;

    @ApiModelProperty(value = "举报内容")
    private String content;

    private Date createdDate;

    private Date updatedDate;

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

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getRespTpye() {
        return respTpye;
    }

    public void setRespTpye(Byte respTpye) {
        this.respTpye = respTpye;
    }

    public Integer getRespTypeId() {
        return respTypeId;
    }

    public void setRespTypeId(Integer respTypeId) {
        this.respTypeId = respTypeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        sb.append(", userId=").append(userId);
        sb.append(", type=").append(type);
        sb.append(", respTpye=").append(respTpye);
        sb.append(", respTypeId=").append(respTypeId);
        sb.append(", content=").append(content);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}