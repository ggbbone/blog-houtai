package com.yzg.blog.portal.controller.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class FeedbackDTO implements Serializable {

    @ApiModelProperty(value = "ip地址")
    private String ip;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "反馈内容")
    private String content;

    @ApiModelProperty(value = "反馈类型")
    private Byte type;

    private Date createdDate;

    private Date updatedDate;

    private static final long serialVersionUID = 1249445L;


}