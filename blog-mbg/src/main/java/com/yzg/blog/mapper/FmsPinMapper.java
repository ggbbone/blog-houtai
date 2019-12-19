package com.yzg.blog.mapper;

import com.yzg.blog.model.FmsPin;
import com.yzg.blog.model.FmsPinExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FmsPinMapper {
    long countByExample(FmsPinExample example);

    int deleteByExample(FmsPinExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FmsPin record);

    int insertSelective(FmsPin record);

    List<FmsPin> selectByExampleWithBLOBs(FmsPinExample example);

    List<FmsPin> selectByExample(FmsPinExample example);

    FmsPin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FmsPin record, @Param("example") FmsPinExample example);

    int updateByExampleWithBLOBs(@Param("record") FmsPin record, @Param("example") FmsPinExample example);

    int updateByExample(@Param("record") FmsPin record, @Param("example") FmsPinExample example);

    int updateByPrimaryKeySelective(FmsPin record);

    int updateByPrimaryKeyWithBLOBs(FmsPin record);

    int updateByPrimaryKey(FmsPin record);
}