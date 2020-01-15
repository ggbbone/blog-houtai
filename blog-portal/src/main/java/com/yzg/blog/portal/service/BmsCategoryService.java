package com.yzg.blog.portal.service;

import com.yzg.blog.portal.model.BmsArticleTag;

import java.util.List;

/**
 * Created by yzg on 2020/1/6
 */
public interface BmsCategoryService {

    /**
     * 添加文章标签
     * @param articleId 文章id
     * @param tags 标签集合
     */
    void insertArticleTags(int articleId, List<Integer> tags);

    /**
     * 删除文章标签
     * @param articleId 文章id
     */
    void deleteArticleTags(int articleId);

    /**
     * 获取文章的标签信息
     * @param articleId 文章id
     */
    List<BmsArticleTag> selectArticleTags(int articleId);

    /**
     * 获取 分类/标签 信息
     * @param categoryId 分类/标签 id
     * @return
     */
    BmsArticleTag select(int categoryId);

    /**
     * 修改标签的文章数量
     * @param ids 标签id
     * @param count 修改数量
     */
    void updateCategoryEntryCount(Integer ids, int count);

    /**
     * 批量修改标签的文章数量
     * @param ids 标签id集合
     * @param count 修改数量
     */
    void updateCategoryEntryCount(List<Integer> ids, int count);

    /**
     * 获取文章的所有标签ids
     * @param articleId 文章id
     * @return 标签的id集合
     */
    List<Integer> getTagIdsByArticleId(int articleId);
}
