package com.yzg.blog.mapper;

import com.yzg.blog.model.UmsAuth;
import com.yzg.blog.model.UmsAuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsAuthMapper {
    long countByExample(UmsAuthExample example);

    int deleteByExample(UmsAuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsAuth record);

    int insertSelective(UmsAuth record);

    List<UmsAuth> selectByExample(UmsAuthExample example);

    UmsAuth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsAuth record, @Param("example") UmsAuthExample example);

    int updateByExample(@Param("record") UmsAuth record, @Param("example") UmsAuthExample example);

    int updateByPrimaryKeySelective(UmsAuth record);

    int updateByPrimaryKey(UmsAuth record);
}