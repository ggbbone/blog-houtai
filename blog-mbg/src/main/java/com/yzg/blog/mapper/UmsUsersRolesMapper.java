package com.yzg.blog.mapper;

import com.yzg.blog.model.UmsUsersRoles;
import com.yzg.blog.model.UmsUsersRolesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsUsersRolesMapper {
    long countByExample(UmsUsersRolesExample example);

    int deleteByExample(UmsUsersRolesExample example);

    int deleteByPrimaryKey(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    int insert(UmsUsersRoles record);

    int insertSelective(UmsUsersRoles record);

    List<UmsUsersRoles> selectByExample(UmsUsersRolesExample example);

    int updateByExampleSelective(@Param("record") UmsUsersRoles record, @Param("example") UmsUsersRolesExample example);

    int updateByExample(@Param("record") UmsUsersRoles record, @Param("example") UmsUsersRolesExample example);
}