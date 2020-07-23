package com.yzg.blog.portal.controller.dto;


import com.yzg.blog.portal.annotation.validation.groups.Insert;
import com.yzg.blog.portal.annotation.validation.groups.Select;
import com.yzg.blog.portal.annotation.validation.groups.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;


@Data
public class CategoryDTO {

    @NotNull(groups = {Update.class})
    @Null(groups = {Insert.class})
    private Integer id;

    @NotBlank(groups = {Insert.class})
    @ApiModelProperty(value = "名称")
    private String title;

    @ApiModelProperty(value = "是否是文章分类")
    private Boolean isCategory;

    @ApiModelProperty(value = "拼音或英文")
    private String alias;

    @ApiModelProperty(value = "图标图片地址")
    private String icon;

    @ApiModelProperty(value = "背景图片地址")
    private String background;

    @ApiModelProperty(value = "排序方式")
    private Integer sort;

    private String orderBy;
}
