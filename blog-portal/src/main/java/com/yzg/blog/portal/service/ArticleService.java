package com.yzg.blog.portal.service;

import com.yzg.blog.portal.common.exception.ValidateFailedException;
import com.yzg.blog.portal.controller.dto.ArticleCreateDTO;
import com.yzg.blog.portal.controller.dto.ArticleListDTO;
import com.yzg.blog.portal.controller.dto.ArticleUpdateDTO;
import com.yzg.blog.portal.model.ArticleInfo;

import java.util.List;

/**
 * Created by yzg on 2019/12/19
 */
public interface ArticleService {

    /**
     * 分页查询所有文章
     * @param pageNum 页码，从1开始
     * @param pageSize 每页条数
     * @return List<BmsArticle>
     */
    List<ArticleInfo> list(int pageNum, int pageSize, ArticleListDTO params);


    /**
     * 增加文章浏览次数
     * @param articleId 文章id
     * @param count 次数
     */
    void addArticleViewCount(int articleId, int count);

    /**
     * 查询单个文章详细信息
     * @param id 文章id
     * @return BmsArticle
     */
    ArticleInfo getById(int id);

    /**
     * 删除单个文章
     * @param id 文章id
     */
    int delete(int id) throws ValidateFailedException;

    /**
     * 更新文章
     * @param params 更新内容
     */
    int update(ArticleUpdateDTO params) throws ValidateFailedException;

    /**
     * 发表文章
     * @param params 文章内容
     */
    int add(ArticleCreateDTO params) throws ValidateFailedException;


    /**
     * 添加文章回复数量
     * @param parentId
     * @param i
     * @return
     */
    int addArticleCommentCount(Integer parentId, int i);
}
