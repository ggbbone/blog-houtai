package com.yzg.blog.mapper;

import com.yzg.blog.model.UmsUserInfoOther;
import com.yzg.blog.model.UmsUserInfoOtherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsUserInfoOtherMapper {
    long countByExample(UmsUserInfoOtherExample example);

    int deleteByExample(UmsUserInfoOtherExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsUserInfoOther record);

    int insertSelective(UmsUserInfoOther record);

    List<UmsUserInfoOther> selectByExample(UmsUserInfoOtherExample example);

    UmsUserInfoOther selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsUserInfoOther record, @Param("example") UmsUserInfoOtherExample example);

    int updateByExample(@Param("record") UmsUserInfoOther record, @Param("example") UmsUserInfoOtherExample example);

    int updateByPrimaryKeySelective(UmsUserInfoOther record);

    int updateByPrimaryKey(UmsUserInfoOther record);
}