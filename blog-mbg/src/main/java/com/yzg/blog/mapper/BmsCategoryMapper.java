package com.yzg.blog.mapper;

import com.yzg.blog.model.BmsCategory;
import com.yzg.blog.model.BmsCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BmsCategoryMapper {
    long countByExample(BmsCategoryExample example);

    int deleteByExample(BmsCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BmsCategory record);

    int insertSelective(BmsCategory record);

    List<BmsCategory> selectByExample(BmsCategoryExample example);

    BmsCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BmsCategory record, @Param("example") BmsCategoryExample example);

    int updateByExample(@Param("record") BmsCategory record, @Param("example") BmsCategoryExample example);

    int updateByPrimaryKeySelective(BmsCategory record);

    int updateByPrimaryKey(BmsCategory record);
}