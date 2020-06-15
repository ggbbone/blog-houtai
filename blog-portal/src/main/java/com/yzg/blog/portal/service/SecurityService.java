package com.yzg.blog.portal.service;

import com.yzg.blog.model.UmsAuth;

import java.util.List;

/**
 * @author yangzg
 */
public interface SecurityService {

    List<UmsAuth> getRolesByUserId(Integer userId);

    List<UmsAuth> getAuthsByRoleId(Integer roleId);

    List<UmsAuth> getAuthsByUserId(Integer userId);

    List<UmsAuth> getAuthsByStatus(Byte status);
}
