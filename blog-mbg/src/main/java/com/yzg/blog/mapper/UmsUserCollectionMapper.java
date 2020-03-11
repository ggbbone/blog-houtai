package com.yzg.blog.mapper;

import com.yzg.blog.model.UmsUserCollection;
import com.yzg.blog.model.UmsUserCollectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsUserCollectionMapper {
    long countByExample(UmsUserCollectionExample example);

    int deleteByExample(UmsUserCollectionExample example);

    int deleteByPrimaryKey(Integer collectionsId);

    int insert(UmsUserCollection record);

    int insertSelective(UmsUserCollection record);

    List<UmsUserCollection> selectByExample(UmsUserCollectionExample example);

    UmsUserCollection selectByPrimaryKey(Integer collectionsId);

    int updateByExampleSelective(@Param("record") UmsUserCollection record, @Param("example") UmsUserCollectionExample example);

    int updateByExample(@Param("record") UmsUserCollection record, @Param("example") UmsUserCollectionExample example);

    int updateByPrimaryKeySelective(UmsUserCollection record);

    int updateByPrimaryKey(UmsUserCollection record);
}