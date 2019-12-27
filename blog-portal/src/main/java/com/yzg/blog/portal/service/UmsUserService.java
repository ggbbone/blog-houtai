package com.yzg.blog.portal.service;

import com.yzg.blog.model.UmsUserInfo;
import com.yzg.blog.portal.dto.UmsLoginParams;
import com.yzg.blog.portal.dto.UmsRegisterParams;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户模块用户注册登录service
 * Created by yzg on 2019/12/24
 */
public interface UmsUserService {
    /**
     * 获取登录验证码
     * @param name 用户名/邮箱
     * @return
     */
    String getLoginCode(String name);

    /**
     * 用户登录
     * @param params
     * @return token
     */
    String login(UmsLoginParams params, HttpServletRequest request);

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    UmsUserInfo getUserById(int id);

    /**
     * 用户注册
     * @param params
     * @return
     */
    String register(UmsRegisterParams params);

    UmsUserInfo getUserByUsername(String username);

    UmsUserInfo getUserByEmail(String email);
}
