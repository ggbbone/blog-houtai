package com.yzg.blog.portal.controller.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * Created by yzg on 2019/12/25
 *
 * 用户模块点赞功能通用请求参数
 */
@Data
public class LikeDTO {
    @ApiModelProperty("点赞的类型（1：文章， 2：讨论， 3：评论/回复）")
    @NotNull
    private Byte type;

    @ApiModelProperty("点赞的目标id")
    @NotNull
    private Integer targetId;

}
