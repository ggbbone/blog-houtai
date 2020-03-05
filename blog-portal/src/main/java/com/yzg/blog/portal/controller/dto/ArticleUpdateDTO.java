package com.yzg.blog.portal.controller.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by yzg on 2019/12/20
 *
 * 修改文章请求参数
 */
@Getter
@Setter
@ToString
public class ArticleUpdateDTO {

    @NotNull
    @ApiModelProperty("文章id")
    private Integer id;

    @NotNull
    @ApiModelProperty("所属类别id")
    private Integer categoryId;

    @NotEmpty
    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("封面图片地址")
    private String cover;

    @NotEmpty
    @ApiModelProperty("正文")
    private String content;

    @ApiModelProperty("绑定标签id集合")
    private List<Integer> tagsId;


}
