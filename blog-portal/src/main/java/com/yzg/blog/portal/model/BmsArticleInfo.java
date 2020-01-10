package com.yzg.blog.portal.model;

import com.yzg.blog.model.BmsArticle;
import com.yzg.blog.model.UmsUserInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * Created by yzg on 2020/1/7
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BmsArticleInfo extends BmsArticle {
    private Integer id;

    @ApiModelProperty(value = "作者信息")
    private UmsUserInfo user;

    @ApiModelProperty(value = "概要")
    private String outline;

    @ApiModelProperty(value = "所属类别")
    private BmsArticleTag category;

    @ApiModelProperty(value = "标签")
    private List<BmsArticleTag> tags;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "封面图片")
    private String cover;

    @ApiModelProperty(value = "创建时间")
    private Date createdDate;

    @ApiModelProperty(value = "最后修改时间")
    private Date updatedDate;

    @ApiModelProperty(value = "最后评论时间")
    private Date lastCommentTime;

    @ApiModelProperty(value = "点赞次数")
    private Integer likeCount;

    @ApiModelProperty(value = "阅读次数")
    private Integer viewCount;

    @ApiModelProperty(value = "评论数量")
    private Integer commentCount;

    @ApiModelProperty(value = "推荐指数")
    private Integer recommendIndex;

    @ApiModelProperty(value = "是否热门文章")
    private Boolean hot;

    @ApiModelProperty(value = "热门指数")
    private Integer hotIndex;

    @ApiModelProperty(value = "正文内容")
    private String content;

    @ApiModelProperty(value = "是否点赞")
    private boolean hasLike;
}
