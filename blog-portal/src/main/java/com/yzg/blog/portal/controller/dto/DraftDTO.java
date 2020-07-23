package com.yzg.blog.portal.controller.dto;

import com.yzg.blog.portal.annotation.validation.groups.Insert;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

@Data
public class DraftDTO implements Serializable {

    @Null(groups = {Insert.class})
    private Integer id;

    @ApiModelProperty(value = "作者id")
    private Integer userId;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "封面图片")
    private String coverUrl;

    @ApiModelProperty(value = "创建时间")
    private Date createdDate;

    @ApiModelProperty(value = "最后修改时间")
    private Date updatedDate;

    @ApiModelProperty(value = "状态（1正常， 0已删除）")
    private Byte status;

    @ApiModelProperty(value = "正文内容markdown")
    private String content;

    private static final long serialVersionUID = 1L;


}