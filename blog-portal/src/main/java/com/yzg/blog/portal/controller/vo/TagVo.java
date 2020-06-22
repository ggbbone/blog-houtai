package com.yzg.blog.portal.controller.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TagVo {
    private Integer id;

    @ApiModelProperty(value = "名称")
    private String title;

    @ApiModelProperty(value = "拼音或英文")
    private String alias;

    @ApiModelProperty(value = "图标图片地址")
    private String icon;

    @ApiModelProperty(value = "背景图片地址")
    private String background;

    private Date createdDate;

    private Date updatedDate;

    @ApiModelProperty(value = "该分类下的文章数量")
    private Integer entryCount;

    @ApiModelProperty(value = "关注人数")
    private Integer followCount;

}
