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

    void updateTagsByArticleId(Integer id, List<Integer> tagIds);

    /**
     * 批量获取标签/分类信息
     * @param ids
     * @return
     */
    List<BmsCategory> getCategoriesByIds(List<Integer> ids);


    void addCategory(CategoryDTO dto);

    /**
     * 查询所有分类
     * @return
     */
    List<BmsCategory> getAllCategory();
}
