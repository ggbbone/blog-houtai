package com.yzg.blog.mapper;

import com.yzg.blog.model.UmsUserLike;
import com.yzg.blog.model.UmsUserLikeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsUserLikeMapper {
    long countByExample(UmsUserLikeExample example);

    int deleteByExample(UmsUserLikeExample example);

    int deleteByPrimaryKey(String id);

    int insert(UmsUserLike record);

    int insertSelective(UmsUserLike record);

    List<UmsUserLike> selectByExampleWithBLOBs(UmsUserLikeExample example);

    List<UmsUserLike> selectByExample(UmsUserLikeExample example);

    UmsUserLike selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UmsUserLike record, @Param("example") UmsUserLikeExample example);

    int updateByExampleWithBLOBs(@Param("record") UmsUserLike record, @Param("example") UmsUserLikeExample example);

    int updateByExample(@Param("record") UmsUserLike record, @Param("example") UmsUserLikeExample example);

    int updateByPrimaryKeySelective(UmsUserLike record);

    int updateByPrimaryKeyWithBLOBs(UmsUserLike record);
}