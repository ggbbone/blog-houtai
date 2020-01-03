package com.yzg.blog.mapper;

import com.yzg.blog.model.BmsArticleTags;
import com.yzg.blog.model.BmsArticleTagsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BmsArticleTagsMapper {
    long countByExample(BmsArticleTagsExample example);

    int deleteByExample(BmsArticleTagsExample example);

    int insert(BmsArticleTags record);

    int insertSelective(BmsArticleTags record);

    List<BmsArticleTags> selectByExample(BmsArticleTagsExample example);

    int updateByExampleSelective(@Param("record") BmsArticleTags record, @Param("example") BmsArticleTagsExample example);

    int updateByExample(@Param("record") BmsArticleTags record, @Param("example") BmsArticleTagsExample example);
}