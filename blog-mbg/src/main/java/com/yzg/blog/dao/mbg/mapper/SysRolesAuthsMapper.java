package com.yzg.blog.dao.mbg.mapper;

import com.yzg.blog.dao.mbg.model.SysRolesAuths;
import com.yzg.blog.dao.mbg.model.SysRolesAuthsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRolesAuthsMapper {
    long countByExample(SysRolesAuthsExample example);

    int deleteByExample(SysRolesAuthsExample example);

    int deleteByPrimaryKey(@Param("roleId") Integer roleId, @Param("authId") Integer authId);

    int insert(SysRolesAuths record);

    int insertSelective(SysRolesAuths record);

    List<SysRolesAuths> selectByExample(SysRolesAuthsExample example);

    int updateByExampleSelective(@Param("record") SysRolesAuths record, @Param("example") SysRolesAuthsExample example);

    int updateByExample(@Param("record") SysRolesAuths record, @Param("example") SysRolesAuthsExample example);
}