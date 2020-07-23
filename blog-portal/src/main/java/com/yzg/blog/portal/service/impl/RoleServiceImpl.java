package com.yzg.blog.portal.service.impl;

import com.yzg.blog.dao.mbg.mapper.SysRoleMapper;
import com.yzg.blog.dao.mbg.mapper.SysUsersRolesMapper;
import com.yzg.blog.dao.mbg.model.SysRole;
import com.yzg.blog.dao.mbg.model.SysRoleExample;
import com.yzg.blog.dao.mbg.model.SysUsersRoles;
import com.yzg.blog.dao.mbg.model.SysUsersRolesExample;
import com.yzg.blog.portal.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    SysRoleMapper roleMapper;

    @Resource
    SysUsersRolesMapper usersRolesMapper;

    @Override
    public List<SysRole> getRolesByUserId(Integer userId) {
        SysUsersRolesExample example = new SysUsersRolesExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<SysUsersRoles> usersRoles = usersRolesMapper.selectByExample(example);
        List<Integer> roleIds = usersRoles.stream().map(SysUsersRoles::getRoleId).collect(Collectors.toList());
        return getRolesByRoleIds(roleIds);
    }

    @Override
    public void addRoleIdsByUserId(ArrayList<Integer> roleIds, Integer id) {
        SysUsersRoles usersRoles = new SysUsersRoles();
        for (Integer roleId : roleIds) {
            usersRoles.setUserId(id);
            usersRoles.setRoleId(roleId);
            usersRolesMapper.insertSelective(usersRoles);
        }
    }

    public List<SysRole> getRolesByRoleIds(List<Integer> roleIds) {
        if (roleIds.size() == 0)
            return new ArrayList<>();
        SysRoleExample example = new SysRoleExample();
        example.createCriteria().andIdIn(roleIds);
        return roleMapper.selectByExample(example);
    }
}
