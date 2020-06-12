package com.yzg.blog.mapper;

import com.yzg.blog.model.UmsRolesAuths;
import com.yzg.blog.model.UmsRolesAuthsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsRolesAuthsMapper {
    long countByExample(UmsRolesAuthsExample example);

    int deleteByExample(UmsRolesAuthsExample example);

    int deleteByPrimaryKey(@Param("roleId") Integer roleId, @Param("authId") Integer authId);

    int insert(UmsRolesAuths record);

    int insertSelective(UmsRolesAuths record);

    List<UmsRolesAuths> selectByExample(UmsRolesAuthsExample example);

    int updateByExampleSelective(@Param("record") UmsRolesAuths record, @Param("example") UmsRolesAuthsExample example);

    int updateByExample(@Param("record") UmsRolesAuths record, @Param("example") UmsRolesAuthsExample example);
}