package com.yzg.blog.portal.service;

import com.yzg.blog.portal.model.BmsArticleTag;

import java.util.List;

/**
 * Created by yzg on 2020/1/6
 */
public interface BmsCategoryService {

    void updateArticleTags(int articleId, List<Integer> tags);

    void insertArticleTags(int articleId, List<Integer> tags);

    List<BmsArticleTag> selectArticleTags(int articleId);

    BmsArticleTag selectArticleCategory(int categoryId);
}
