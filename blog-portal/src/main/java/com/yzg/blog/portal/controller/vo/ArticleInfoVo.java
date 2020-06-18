package com.yzg.blog.portal.controller.vo;

import com.yzg.blog.dao.mbg.model.BmsCategory;
import com.yzg.blog.dao.mbg.model.UmsUserInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author yangzg
 */
@Data
public class ArticleInfoVo {
    private Integer id;

    @ApiModelProperty(value = "作者id")
    private Integer userId;

    @ApiModelProperty(value = "作者信息")
    private UmsUserInfo userInfo;

    @ApiModelProperty(value = "概要")
    private String content;

    @ApiModelProperty(value = "所属类别id")
    private Integer categoryId;

    @ApiModelProperty(value = "所属类别")
    private BmsCategory category;

    @ApiModelProperty(value = "标签集合")
    private List<BmsCategory> tags;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "是否有封面图片")
    private Boolean cover;

    @ApiModelProperty(value = "封面图片url")
    private String coveUrl;

    @ApiModelProperty(value = "创建时间")
    private Date createdDate;

    @ApiModelProperty(value = "最后修改时间")
    private Date updatedDate;

    @ApiModelProperty(value = "最后评论时间")
    private Date lastCommentTime;

    @ApiModelProperty(value = "阅读次数")
    private Integer viewCount;

    @ApiModelProperty(value = "点赞次数")
    private Integer likeCount;

    @ApiModelProperty(value = "评论数量")
    private Integer commentCount;

    @ApiModelProperty(value = "推荐指数")
    private Integer recommendIndex;

    @ApiModelProperty(value = "是否热门文章")
    private Boolean hot;

    @ApiModelProperty(value = "热门指数")
    private Integer hotIndex;

    @ApiModelProperty(value = "状态（1正常， 2已删除， 3已屏蔽）")
    private Byte status;

    @ApiModelProperty(value = "正文内容html")
    private String html;

    @ApiModelProperty(value = "正文内容markdown")
    private String markdown;


}
