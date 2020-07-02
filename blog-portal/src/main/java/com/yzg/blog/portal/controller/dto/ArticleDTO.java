package com.yzg.blog.portal.controller.dto;


import com.yzg.blog.portal.annotation.validation.groups.Insert;
import com.yzg.blog.portal.annotation.validation.groups.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Data
public class ArticleDTO {

    @NotNull(groups = {Update.class})
    private Integer id;

    @NotNull(groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "所属类别id")
    private Integer categoryId;

    @ApiModelProperty(value = "排序方式")
    private Integer sort;

    private String orderBy;

    @ApiModelProperty(value = "标签查询条件")
    private Integer tagId;

    @NotNull(groups = {Insert.class, Update.class})
    @Size(max = 5, groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "标签id集合")
    private List<Integer> tagIds;

    @ApiModelProperty(value = "作者id")
    private Integer userId;

    @NotBlank(groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "标题")
    private String title;

    @NotNull(groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "是否有封面图片")
    private Boolean cover;

    @NotNull(groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "封面图片url")
    private String coverUrl;

    @NotNull(groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "正文内容html")
    private String html;

    @NotNull(groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "正文内容markdown")
    private String markdown;

}
