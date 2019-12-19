package com.yzg.blog.mapper;

import com.yzg.blog.model.CmsComment;
import com.yzg.blog.model.CmsCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsCommentMapper {
    long countByExample(CmsCommentExample example);

    int deleteByExample(CmsCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsComment record);

    int insertSelective(CmsComment record);

    List<CmsComment> selectByExample(CmsCommentExample example);

    CmsComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CmsComment record, @Param("example") CmsCommentExample example);

    int updateByExample(@Param("record") CmsComment record, @Param("example") CmsCommentExample example);

    int updateByPrimaryKeySelective(CmsComment record);

    int updateByPrimaryKey(CmsComment record);
}