package com.yzg.blog.portal.controller.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by yzg on 2019/12/25
 *
 * 用户模块点赞功能通用请求参数
 */
@Data
public class FollowDTO {
    @ApiModelProperty("关注的类型（1：用户， 2：文章分类/标签）")
    @NotNull
    private Byte type;

    @ApiModelProperty("关注的目标id")
    @NotNull
    private Integer targetId;

}
