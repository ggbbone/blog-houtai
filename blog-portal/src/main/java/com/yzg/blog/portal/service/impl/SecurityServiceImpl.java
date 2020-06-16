package com.yzg.blog.portal.service.impl;

;
import com.yzg.blog.dao.mbg.mapper.UmsAuthMapper;
import com.yzg.blog.dao.mbg.mapper.UmsRoleMapper;
import com.yzg.blog.dao.mbg.mapper.UmsRolesAuthsMapper;
import com.yzg.blog.dao.mbg.mapper.UmsUsersRolesMapper;
import com.yzg.blog.dao.mbg.model.UmsAuth;
import com.yzg.blog.portal.service.SecurityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Resource
    UmsAuthMapper authMapper;
    @Resource
    UmsRoleMapper umsRoleMapper;
    @Resource
    UmsRolesAuthsMapper rolesAuthsMapper;
    @Resource
    UmsUsersRolesMapper usersRolesMapper;

    @Override
    public List<UmsAuth> getRolesByUserId(Integer userId) {

        return null;
    }

    @Override
    public List<UmsAuth> getAuthsByRoleId(Integer roleId) {
        return null;
    }

    @Override
    public List<UmsAuth> getAuthsByUserId(Integer userId) {

        return null;
    }

    @Override
    public List<UmsAuth> getAuthsByStatus(Byte status) {
        return null;
    }
}
