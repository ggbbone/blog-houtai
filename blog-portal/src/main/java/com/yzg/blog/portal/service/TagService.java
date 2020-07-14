package com.yzg.blog.portal.service;

import com.yzg.blog.dao.mbg.model.BmsCategory;
import com.yzg.blog.portal.controller.dto.CategoryDTO;
import org.springframework.cache.annotation.CacheEvict;

import java.util.List;

public interface TagService {
    List<Integer> getTagIdsByArticleId(Integer id);

    /**
     * 删除文章的所有标签
     * @param articleId
     */
    void deleteTagsByArticleId(Integer articleId);

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

    /**
     * 添加文章标签
     * @param dto
     */
    Integer addTag(CategoryDTO dto);

    /**
     * 获取标签列表
     * @param dto
     * @return
     */
    List<BmsCategory> getTagsList(CategoryDTO dto);
}
