package com.yzg.blog.portal.dao;

import com.yzg.blog.dao.mbg.model.BmsCategory;
import com.yzg.blog.portal.controller.dto.ArticleDTO;
import com.yzg.blog.portal.controller.dto.CategoryDTO;
import com.yzg.blog.portal.controller.vo.ArticleInfoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author yangzg
 */
@Mapper
public interface TagDao {


    List<BmsCategory> getTags(CategoryDTO dto);

    void addEntryCountByTagIds(List<Integer> tagIds);

    void lessEntryCountByTagIds(List<Integer> tagIds);
}
