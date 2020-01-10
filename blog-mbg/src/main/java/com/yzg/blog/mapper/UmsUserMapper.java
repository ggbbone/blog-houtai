package com.yzg.blog.mapper;

import com.yzg.blog.model.UmsUser;
import com.yzg.blog.model.UmsUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsUserMapper {
    long countByExample(UmsUserExample example);

    int deleteByExample(UmsUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsUser record);

    int insertSelective(UmsUser record);

    List<UmsUser> selectByExample(UmsUserExample example);

    UmsUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsUser record, @Param("example") UmsUserExample example);

    int updateByExample(@Param("record") UmsUser record, @Param("example") UmsUserExample example);

    int updateByPrimaryKeySelective(UmsUser record);

    int updateByPrimaryKey(UmsUser record);
}