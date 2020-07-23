package com.yzg.blog.portal.dao;

import com.yzg.blog.portal.controller.dto.ArticleDTO;
import com.yzg.blog.portal.controller.vo.ArticleInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yangzg
 */
@Mapper
public interface ArticleDao {


    List<ArticleInfoVo> selectArticleList(ArticleDTO dto);

    int updateViewCountById(@Param("id") Integer id, @Param("count") int viewCount);

    int updateCommentCountById(@Param("id") Integer id, @Param("count") int count);

}
