package com.yzg.blog.portal.controller.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by yzg on 2019/12/20
 * <p>
 * 分页查询评论列表参数
 */
public class CmsCommentListParams {

    @ApiModelProperty("回复的目标评论id，为0时表示评论")
    private int targetId;

    @ApiModelProperty("父类型，1：文章， 2：讨论")
    @NotNull
    private Byte parentType;

    @ApiModelProperty("父类型对象id")
    @NotNull
    private Integer parentId;

    @ApiModelProperty("排序字段")
    @NotBlank
    private String orderBy;

    @Override
    public String toString() {
        return "CmsCommentListParams{" +
                "targetId=" + targetId +
                ", parentType=" + parentType +
                ", parentId=" + parentId +
                ", orderBy='" + orderBy + '\'' +
                '}';
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public Byte getParentType() {
        return parentType;
    }

    public void setParentType(Byte parentType) {
        this.parentType = parentType;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
