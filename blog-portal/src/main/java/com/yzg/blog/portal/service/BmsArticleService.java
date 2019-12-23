package com.yzg.blog.portal.service;

import com.yzg.blog.model.BmsArticle;
import com.yzg.blog.portal.dto.BmsArticleUpdateParams;

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
    List<BmsArticle> list(int pageNum, int pageSize);

    /**
     * 查询单个文章详细信息
     * @param id 文章id
     * @return BmsArticle
     */
    BmsArticle getById(int id);

    /**
     * 删除单个文章
     * @param id 文章id
     */
    void delete(int id);

    /**
     * 更新文章
     * @param params 更新内容
     */
    void update(BmsArticleUpdateParams params);
}
