package com.yzg.blog.mapper;

import com.yzg.blog.model.UmsActiveityFeed;
import com.yzg.blog.model.UmsActiveityFeedExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsActiveityFeedMapper {
    long countByExample(UmsActiveityFeedExample example);

    int deleteByExample(UmsActiveityFeedExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsActiveityFeed record);

    int insertSelective(UmsActiveityFeed record);

    List<UmsActiveityFeed> selectByExample(UmsActiveityFeedExample example);

    UmsActiveityFeed selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsActiveityFeed record, @Param("example") UmsActiveityFeedExample example);

    int updateByExample(@Param("record") UmsActiveityFeed record, @Param("example") UmsActiveityFeedExample example);

    int updateByPrimaryKeySelective(UmsActiveityFeed record);

    int updateByPrimaryKey(UmsActiveityFeed record);
}