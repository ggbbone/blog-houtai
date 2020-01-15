package com.yzg.blog.portal.service;

import com.yzg.blog.model.UmsUser;
import com.yzg.blog.portal.controller.dto.UmsLoginParams;
import com.yzg.blog.portal.controller.dto.UmsRegisterParams;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户模块用户注册登录service
 * Created by yzg on 2019/12/24
 */
public interface UmsUserService {

    /**
     * 用户登录
     * @param params
     * @return token
     */
    String login(UmsLoginParams params, HttpServletRequest request) throws Exception;

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    UmsUser getUserById(int id);

    /**
     * 用户注册
     * @param params
     * @return
     */
    String register(UmsRegisterParams params) throws Exception;


    /**
     * 根据邮箱查询用户
     * @param email
     * @return
     */
    UmsUser getUserByEmail(String email);
}
