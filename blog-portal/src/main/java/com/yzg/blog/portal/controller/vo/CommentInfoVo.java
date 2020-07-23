package com.yzg.blog.portal.controller.vo;

import com.yzg.blog.dao.mbg.model.UmsUserInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CommentInfoVo implements Serializable {

    private Integer id;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "点赞人数")
    private Integer likesCount;

    @ApiModelProperty(value = "子回复数量")
    private Integer replyCount;

    @ApiModelProperty(value = "发表用户id")
    private Integer userId;

    private UmsUserInfo userInfo;

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

    @ApiModelProperty(value = "评论目标id")
    private Integer parentId;

    @ApiModelProperty(value = "评论类型 (1:文章， 2讨论 )")
    private Byte parentType;

    private static final long serialVersionUID = 876345L;


}