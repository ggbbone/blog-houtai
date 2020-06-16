package com.yzg.blog.dao.mbg.mapper;

import com.yzg.blog.dao.mbg.model.BmsArticleDraft;
import com.yzg.blog.dao.mbg.model.BmsArticleDraftExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BmsArticleDraftMapper {
    long countByExample(BmsArticleDraftExample example);

    int deleteByExample(BmsArticleDraftExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BmsArticleDraft record);

    int insertSelective(BmsArticleDraft record);

    List<BmsArticleDraft> selectByExampleWithBLOBs(BmsArticleDraftExample example);

    List<BmsArticleDraft> selectByExample(BmsArticleDraftExample example);

    BmsArticleDraft selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BmsArticleDraft record, @Param("example") BmsArticleDraftExample example);

    int updateByExampleWithBLOBs(@Param("record") BmsArticleDraft record, @Param("example") BmsArticleDraftExample example);

    int updateByExample(@Param("record") BmsArticleDraft record, @Param("example") BmsArticleDraftExample example);

    int updateByPrimaryKeySelective(BmsArticleDraft record);

    int updateByPrimaryKeyWithBLOBs(BmsArticleDraft record);

    int updateByPrimaryKey(BmsArticleDraft record);
}