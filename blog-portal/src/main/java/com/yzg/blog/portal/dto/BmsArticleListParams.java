package com.yzg.blog.portal.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by yzg on 2019/12/24
 *
 * 查询文章列表请求参数封装
 */
public class BmsArticleListParams {
    @ApiModelProperty("发表用户id")
    private Integer userId;

    @ApiModelProperty("文章所属分类id")
    private Integer categoryId;

    @ApiModelProperty("是否是热门文章")
    private boolean hot;

    @ApiModelProperty("排序字段")
    @NotNull
    private String orderBy;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getHot() {
        return hot;
    }

    public void setHot(Boolean hot) {
        this.hot = hot;
    }
}
