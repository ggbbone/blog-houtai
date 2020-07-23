package com.yzg.blog.dao.mbg.mapper;

import com.yzg.blog.dao.mbg.model.SysUsersRoles;
import com.yzg.blog.dao.mbg.model.SysUsersRolesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUsersRolesMapper {
    long countByExample(SysUsersRolesExample example);

    int deleteByExample(SysUsersRolesExample example);

    int deleteByPrimaryKey(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    int insert(SysUsersRoles record);

    int insertSelective(SysUsersRoles record);

    List<SysUsersRoles> selectByExample(SysUsersRolesExample example);

    int updateByExampleSelective(@Param("record") SysUsersRoles record, @Param("example") SysUsersRolesExample example);

    int updateByExample(@Param("record") SysUsersRoles record, @Param("example") SysUsersRolesExample example);
}