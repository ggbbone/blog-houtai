package com.yzg.blog.mapper;

import com.yzg.blog.model.UmsActivityFeed;
import com.yzg.blog.model.UmsActivityFeedExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsActivityFeedMapper {
    long countByExample(UmsActivityFeedExample example);

    int deleteByExample(UmsActivityFeedExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsActivityFeed record);

    int insertSelective(UmsActivityFeed record);

    List<UmsActivityFeed> selectByExample(UmsActivityFeedExample example);

    UmsActivityFeed selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsActivityFeed record, @Param("example") UmsActivityFeedExample example);

    int updateByExample(@Param("record") UmsActivityFeed record, @Param("example") UmsActivityFeedExample example);

    int updateByPrimaryKeySelective(UmsActivityFeed record);

    int updateByPrimaryKey(UmsActivityFeed record);
}