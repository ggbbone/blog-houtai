package com.yzg.blog.portal.dto;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by yzg on 2019/12/20
 *
 * 评论模块发表评论参数
 */
public class CmsCommentCreateParams {
    @ApiModelProperty("评论内容")
    @NotEmpty(message = "评论的内容不能为空")
    @Length(min = 6, message = "评论的内容过短")
    @Length(max = 256, message = "评论的内容过长")
    private String content;

    @ApiModelProperty("评论所属父评论id")
    @NotNull(message = "评论所属父评论id不能为空")
    private Integer targetId;

    @ApiModelProperty("评论的接收用户id")
    @NotNull(message = "评论的接收用户id不能为空")
    private Integer respUserId;

    @ApiModelProperty("评论的类型（1：文章， 2：讨论")
    @NotNull(message = "评论的类型不能为空")
    private Byte type;

    @ApiModelProperty("评论的父类对象id")
    @NotNull(message = "评论的父类对象id不能为空")
    private Integer parentId;

    @Override
    public String toString() {
        return "CmsCommentAddParams{" +
                "content='" + content + '\'' +
                ", targetId=" + targetId +
                ", respUserId=" + respUserId +
                ", type=" + type +
                '}';
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public Integer getRespUserId() {
        return respUserId;
    }

    public void setRespUserId(Integer respUserId) {
        this.respUserId = respUserId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
