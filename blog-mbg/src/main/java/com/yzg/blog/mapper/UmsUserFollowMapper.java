package com.yzg.blog.mapper;

import com.yzg.blog.model.UmsUserFollow;
import com.yzg.blog.model.UmsUserFollowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsUserFollowMapper {
    long countByExample(UmsUserFollowExample example);

    int deleteByExample(UmsUserFollowExample example);

    int insert(UmsUserFollow record);

    int insertSelective(UmsUserFollow record);

    List<UmsUserFollow> selectByExample(UmsUserFollowExample example);

    int updateByExampleSelective(@Param("record") UmsUserFollow record, @Param("example") UmsUserFollowExample example);

    int updateByExample(@Param("record") UmsUserFollow record, @Param("example") UmsUserFollowExample example);
}