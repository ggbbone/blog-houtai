package com.yzg.blog.dao.mbg.mapper;

import com.yzg.blog.dao.mbg.model.UmsUserFeedback;
import com.yzg.blog.dao.mbg.model.UmsUserFeedbackExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsUserFeedbackMapper {
    long countByExample(UmsUserFeedbackExample example);

    int deleteByExample(UmsUserFeedbackExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsUserFeedback record);

    int insertSelective(UmsUserFeedback record);

    List<UmsUserFeedback> selectByExample(UmsUserFeedbackExample example);

    UmsUserFeedback selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsUserFeedback record, @Param("example") UmsUserFeedbackExample example);

    int updateByExample(@Param("record") UmsUserFeedback record, @Param("example") UmsUserFeedbackExample example);

    int updateByPrimaryKeySelective(UmsUserFeedback record);

    int updateByPrimaryKey(UmsUserFeedback record);
}