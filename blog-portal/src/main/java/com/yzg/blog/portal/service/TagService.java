package com.yzg.blog.portal.service;

import com.yzg.blog.dao.mbg.model.BmsCategory;
import org.springframework.cache.annotation.CacheEvict;

import java.util.List;

public interface TagService {
    List<Integer> getTagIdsByArticleId(Integer id);

    /**
     * 删除文章的所有标签
     * @param id
     */
    void deleteTagsByArticleId(Integer id);

    List<Integer> getArticleIdsByTagId(Integer tagId);

    /**
     * 查询文章的标签信息
     * @param id
     * @return
     */
    List<BmsCategory> getTagsByArticleId(Integer id);

    /**
     * 添加文章标签
     * @param tagIds
     * @param id
     */
    void addArticleTags(List<Integer> tagIds, Integer id);

}
