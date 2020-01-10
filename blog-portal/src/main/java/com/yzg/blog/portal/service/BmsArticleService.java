package com.yzg.blog.portal.service;

import com.yzg.blog.portal.dto.BmsArticleCreateParams;
import com.yzg.blog.portal.dto.BmsArticleListParams;
import com.yzg.blog.portal.dto.BmsArticleUpdateParams;
import com.yzg.blog.portal.model.BmsArticleInfo;

import java.util.List;

/**
 * Created by yzg on 2019/12/19
 */
public interface BmsArticleService {

    /**
     * 分页查询所有文章
     * @param pageNum 页码，从1开始
     * @param pageSize 每页条数
     * @return List<BmsArticle>
     */
    List<BmsArticleInfo> list(int pageNum, int pageSize, BmsArticleListParams params);


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
    BmsArticleInfo getById(int id);

    /**
     * 删除单个文章
     * @param id 文章id
     */
    int delete(int id);

    /**
     * 更新文章
     * @param params 更新内容
     */
    int update(BmsArticleUpdateParams params);

    /**
     * 发表文章
     * @param params 文章内容
     */
    int add(BmsArticleCreateParams params);


}
