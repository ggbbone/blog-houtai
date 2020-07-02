package com.yzg.blog.portal.controller.vo;

import com.yzg.blog.dao.mbg.model.BmsCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ArticleTagVo  implements Serializable {
    private Integer id;

    @ApiModelProperty(value = "名称")
    private String title;

    @ApiModelProperty(value = "图标图片地址")
    private String icon;

    public ArticleTagVo() {
    }

    public ArticleTagVo(Integer id, String title, String icon) {
        this.id = id;
        this.title = title;
        this.icon = icon;
    }

    public ArticleTagVo(BmsCategory categoryById) {
        if (categoryById != null) {
            this.id = categoryById.getId();
            this.title = categoryById.getTitle();
            this.icon = categoryById.getIcon();
        }
    }


}
