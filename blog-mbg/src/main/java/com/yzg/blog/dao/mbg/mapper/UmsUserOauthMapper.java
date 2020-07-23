package com.yzg.blog.dao.mbg.mapper;

import com.yzg.blog.dao.mbg.model.UmsUserOauth;
import com.yzg.blog.dao.mbg.model.UmsUserOauthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsUserOauthMapper {
    long countByExample(UmsUserOauthExample example);

    int deleteByExample(UmsUserOauthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsUserOauth record);

    int insertSelective(UmsUserOauth record);

    List<UmsUserOauth> selectByExample(UmsUserOauthExample example);

    UmsUserOauth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsUserOauth record, @Param("example") UmsUserOauthExample example);

    int updateByExample(@Param("record") UmsUserOauth record, @Param("example") UmsUserOauthExample example);

    int updateByPrimaryKeySelective(UmsUserOauth record);

    int updateByPrimaryKey(UmsUserOauth record);
}