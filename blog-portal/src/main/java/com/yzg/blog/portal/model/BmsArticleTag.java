package com.yzg.blog.portal.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by yzg on 2020/1/6
 *
 * 单个文章标签信息
 */
@Data
public class BmsArticleTag implements Serializable {
    private String title;
    private Integer id;
}
