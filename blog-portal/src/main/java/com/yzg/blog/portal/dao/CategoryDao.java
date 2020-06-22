package com.yzg.blog.portal.dao;

import com.yzg.blog.dao.mbg.model.BmsCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author yangzg
 *
 */

@Mapper
public interface CategoryDao {

    List<BmsCategory> getTagsByArticleId(Integer id);
}
