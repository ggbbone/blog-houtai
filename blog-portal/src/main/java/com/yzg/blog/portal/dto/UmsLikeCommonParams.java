package com.yzg.blog.portal.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by yzg on 2019/12/25
 *
 * 用户模块点赞功能通用请求参数
 */
public class UmsLikeCommonParams {
    @ApiModelProperty("点赞的类型（1：文章， 2：讨论， 3：评论/回复）")
    @NotNull
    private Byte type;

    @ApiModelProperty("点赞的目标id")
    @NotNull
    private Integer targetId;

    @Override
    public String toString() {
        return "UmsLikeCommonParams{" +
                "type='" + type + '\'' +
                ", targetId=" + targetId +
                '}';
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }
}
