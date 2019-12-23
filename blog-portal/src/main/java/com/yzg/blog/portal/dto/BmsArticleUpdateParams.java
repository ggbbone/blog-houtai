package com.yzg.blog.portal.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by yzg on 2019/12/20
 *
 * 修改文章请求参数
 */
public class BmsArticleUpdateParams {

    @NotNull(message = "文章id不能为空")
    @ApiModelProperty("修改的文章id")
    private Integer id;

    @ApiModelProperty("文章的所属类别id")
    private Integer category_id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("封面图片地址")
    private String cover;

    @ApiModelProperty("正文")
    private String content;

    @ApiModelProperty("绑定标签id集合")
    private List<Integer> tagsId;

    @Override
    public String toString() {
        return "BmsArticleUpdateParams{" +
                "id=" + id +
                ", category_id=" + category_id +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", content='" + content + '\'' +
                ", tagsId=" + tagsId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Integer> getTagsId() {
        return tagsId;
    }

    public void setTagsId(List<Integer> tagsId) {
        this.tagsId = tagsId;
    }
}
