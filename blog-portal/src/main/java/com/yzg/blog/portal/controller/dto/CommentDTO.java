package com.yzg.blog.portal.controller.dto;

import com.yzg.blog.portal.annotation.validation.groups.Insert;
import com.yzg.blog.portal.annotation.validation.groups.Select;
import com.yzg.blog.portal.annotation.validation.groups.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class CommentDTO implements Serializable {
    @NotNull(groups = {Update.class})
    private Integer id;

    @NotNull(groups = {Insert.class, Update.class})
    @NotBlank(groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "排序方式")
    private Integer sort;

    @ApiModelProperty(value = "点赞人数")
    private Integer likesCount;

    @ApiModelProperty(value = "子回复数量")
    private Integer replyCount;

    @ApiModelProperty(value = "发表用户id")
    private Integer userId;

    @ApiModelProperty(value = "游客昵称")
    private String nickname;

    @ApiModelProperty(value = "游客邮箱")
    private String email;

    @ApiModelProperty(value = "评论目标用户id")
    private Integer respUserId;

    @ApiModelProperty(value = "父评论id，为0时表示没有父评论")
    private Integer targetId;

    @ApiModelProperty(value = "创建时间")
    private Date createdDate;

    @ApiModelProperty(value = "最后更新时间")
    private Date updatedDate;

    @ApiModelProperty(value = "状态 （1：正常， 2已删除， 3已屏蔽）")
    private Byte status;

    @NotNull(groups = {Insert.class})
    @ApiModelProperty(value = "评论目标id")
    private Integer parentId;

    @NotNull(groups = {Insert.class})
    @ApiModelProperty(value = "评论类型 (1:文章， 2讨论 )")
    private Byte parentType;

    private static final long serialVersionUID = 43656965L;


}