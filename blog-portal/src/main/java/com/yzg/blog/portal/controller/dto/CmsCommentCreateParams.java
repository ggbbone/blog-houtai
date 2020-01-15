package com.yzg.blog.portal.controller.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by yzg on 2019/12/20
 *
 * 评论模块发表评论参数
 */
@Data
public class CmsCommentCreateParams {
    @ApiModelProperty("评论内容")
    @NotEmpty()
    @Length(min = 6)
    @Length(max = 256)
    private String content;

    @ApiModelProperty("父评论id，为0时表示父评论")
    @NotNull()
    private Integer targetId;

    @ApiModelProperty("评论的接收用户id")
    @NotNull()
    private Integer respUserId;

    @ApiModelProperty("评论的类型（1：文章， 2：讨论")
    @NotNull()
    private Byte parentType;

    @ApiModelProperty("评论的父类对象id")
    @NotNull()
    private Integer parentId;

}
