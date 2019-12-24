package com.yzg.blog.portal.service;

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
}
