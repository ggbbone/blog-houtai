package com.yzg.blog.dao.mbg.mapper;

import com.yzg.blog.dao.mbg.model.UmsFeedInfo;
import com.yzg.blog.dao.mbg.model.UmsFeedInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsFeedInfoMapper {
    long countByExample(UmsFeedInfoExample example);

    int deleteByExample(UmsFeedInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsFeedInfo record);

    int insertSelective(UmsFeedInfo record);

    List<UmsFeedInfo> selectByExample(UmsFeedInfoExample example);

    UmsFeedInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsFeedInfo record, @Param("example") UmsFeedInfoExample example);

    int updateByExample(@Param("record") UmsFeedInfo record, @Param("example") UmsFeedInfoExample example);

    int updateByPrimaryKeySelective(UmsFeedInfo record);

    int updateByPrimaryKey(UmsFeedInfo record);
}