package com.yzg.blog.dao.mbg.mapper;

import com.yzg.blog.dao.mbg.model.UmsUserInfo;
import com.yzg.blog.dao.mbg.model.UmsUserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsUserInfoMapper {
    long countByExample(UmsUserInfoExample example);

    int deleteByExample(UmsUserInfoExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(UmsUserInfo record);

    int insertSelective(UmsUserInfo record);

    List<UmsUserInfo> selectByExample(UmsUserInfoExample example);

    UmsUserInfo selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") UmsUserInfo record, @Param("example") UmsUserInfoExample example);

    int updateByExample(@Param("record") UmsUserInfo record, @Param("example") UmsUserInfoExample example);

    int updateByPrimaryKeySelective(UmsUserInfo record);

    int updateByPrimaryKey(UmsUserInfo record);
}