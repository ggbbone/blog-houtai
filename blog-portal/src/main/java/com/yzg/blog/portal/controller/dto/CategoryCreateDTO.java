package com.yzg.blog.portal.controller.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * Created by yzg on 2020/3/5
 */
@Data
public class CategoryCreateDTO {

    @ApiModelProperty(value = "名称")
    @NotEmpty
    private String title;

    @ApiModelProperty(value = "拼音或英文")
    private String alias;

    @ApiModelProperty(value = "图标图片地址")
    private String icon;

    @ApiModelProperty(value = "背景图片地址")
    private String background;

}
