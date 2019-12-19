package com.yzg.blog.mapper;

import com.yzg.blog.model.BmsArticle;
import com.yzg.blog.model.BmsArticleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BmsArticleMapper {
    long countByExample(BmsArticleExample example);

    int deleteByExample(BmsArticleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BmsArticle record);

    int insertSelective(BmsArticle record);

    List<BmsArticle> selectByExampleWithBLOBs(BmsArticleExample example);

    List<BmsArticle> selectByExample(BmsArticleExample example);

    BmsArticle selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BmsArticle record, @Param("example") BmsArticleExample example);

    int updateByExampleWithBLOBs(@Param("record") BmsArticle record, @Param("example") BmsArticleExample example);

    int updateByExample(@Param("record") BmsArticle record, @Param("example") BmsArticleExample example);

    int updateByPrimaryKeySelective(BmsArticle record);

    int updateByPrimaryKeyWithBLOBs(BmsArticle record);

    int updateByPrimaryKey(BmsArticle record);
}