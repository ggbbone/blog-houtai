package com.yzg.blog.portal.controller.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * Created by yzg on 2020/1/3
 */
@Getter
@Setter
@ToString
public class ArticleDraftCreateDTO {

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "封面图片")
    private String cover;

    @ApiModelProperty(value = "正文内容")
    @NotNull
    private String content;

}
