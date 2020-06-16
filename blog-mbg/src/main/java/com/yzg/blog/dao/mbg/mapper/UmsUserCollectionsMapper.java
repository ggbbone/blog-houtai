package com.yzg.blog.dao.mbg.mapper;

import com.yzg.blog.dao.mbg.model.UmsUserCollections;
import com.yzg.blog.dao.mbg.model.UmsUserCollectionsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsUserCollectionsMapper {
    long countByExample(UmsUserCollectionsExample example);

    int deleteByExample(UmsUserCollectionsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsUserCollections record);

    int insertSelective(UmsUserCollections record);

    List<UmsUserCollections> selectByExample(UmsUserCollectionsExample example);

    UmsUserCollections selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsUserCollections record, @Param("example") UmsUserCollectionsExample example);

    int updateByExample(@Param("record") UmsUserCollections record, @Param("example") UmsUserCollectionsExample example);

    int updateByPrimaryKeySelective(UmsUserCollections record);

    int updateByPrimaryKey(UmsUserCollections record);
}