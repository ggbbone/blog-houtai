package com.yzg.blog.portal.controller.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by yzg on 2019/12/24
 *
 * 查询文章列表请求参数封装
 */
@Data
public class BmsArticleListParams implements Serializable {
    @ApiModelProperty("发表用户id")
    private Integer userId;

    @ApiModelProperty("文章所属分类id")
    private Integer categoryId;

    @ApiModelProperty("排序字段")
    private Byte orderBy;

}
