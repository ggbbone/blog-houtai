package com.yzg.blog.mapper;

import com.yzg.blog.model.CmsReport;
import com.yzg.blog.model.CmsReportExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsReportMapper {
    long countByExample(CmsReportExample example);

    int deleteByExample(CmsReportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsReport record);

    int insertSelective(CmsReport record);

    List<CmsReport> selectByExample(CmsReportExample example);

    CmsReport selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CmsReport record, @Param("example") CmsReportExample example);

    int updateByExample(@Param("record") CmsReport record, @Param("example") CmsReportExample example);

    int updateByPrimaryKeySelective(CmsReport record);

    int updateByPrimaryKey(CmsReport record);
}