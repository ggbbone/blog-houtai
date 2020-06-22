package com.yzg.blog.dao.mbg.mapper;

import com.yzg.blog.dao.mbg.model.BmsArticleTags;
import com.yzg.blog.dao.mbg.model.BmsArticleTagsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BmsArticleTagsMapper {
    long countByExample(BmsArticleTagsExample example);

    int deleteByExample(BmsArticleTagsExample example);

    int deleteByPrimaryKey(@Param("articleId") Integer articleId, @Param("categoryId") Integer categoryId);

    int insert(BmsArticleTags record);

    int insertSelective(BmsArticleTags record);

    List<BmsArticleTags> selectByExample(BmsArticleTagsExample example);

    int updateByExampleSelective(@Param("record") BmsArticleTags record, @Param("example") BmsArticleTagsExample example);

    int updateByExample(@Param("record") BmsArticleTags record, @Param("example") BmsArticleTagsExample example);
}