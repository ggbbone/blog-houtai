package com.yzg.blog.portal.service;

import com.yzg.blog.dao.mbg.model.SysRole;

import java.util.ArrayList;
import java.util.List;

public interface RoleService {


    List<SysRole> getRolesByUserId(Integer userId);

    void addRoleIdsByUserId(ArrayList<Integer> roleIds, Integer id);
}
