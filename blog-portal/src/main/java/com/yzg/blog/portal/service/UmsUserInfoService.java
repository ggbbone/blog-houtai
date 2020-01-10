package com.yzg.blog.portal.service;

import com.yzg.blog.model.UmsUserInfo;

/**
 * Created by yzg on 2020/1/3
 *
 * 用户详细信息service
 */
public interface UmsUserInfoService {

    /**
     * 根据id获取用户详细信息
     * @param id 用户id
     * @return 用户详细信息
     */
    UmsUserInfo getUserInfoById(Integer id);

    /**
     * 根据用户名查询用户详细信息
     *
     * @param username 用户名
     * @return 用户详细信息
     */
    UmsUserInfo getUserInfoByUsername(String username);
}
