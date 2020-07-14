package com.yzg.blog.portal.service;

import com.yzg.blog.dao.mbg.model.BmsCategory;
import com.yzg.blog.portal.controller.dto.CategoryDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yangzg
 */
public interface CategoryService {

    /**
     * 根据id获取标签/分类信息
     * @param id
     * @return
     */
    BmsCategory getCategoryById(Integer id);

    /**
     * 更新文章的标签
     * @param id
     * @param tagIds
     */
    void updateTagsByArticleId(Integer id, List<Integer> tagIds);

    /**
     * 批量获取标签/分类信息
     * @param ids
     * @return
     */
    List<BmsCategory> getCategoriesByIds(List<Integer> ids);

    /**
     * 添加文章分类
     * @param dto
     */
    void addCategory(CategoryDTO dto);

    /**
     * 查询所有分类
     * @return
     */
    List<BmsCategory> getAllCategory();
}
