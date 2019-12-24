package com.yzg.blog.portal.controller;

import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.portal.service.UmsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户模块注册登录管理controller
 * Created by yzg on 2019/12/24
 */
@Validated
@RestController
@RequestMapping(value = "/sso")
@Api(tags = "用户模块登陆管理")
public class UmsUserController {
    @Autowired
    private UmsUserService userService;

    /**
     * 登录获取验证码
     * @param name 邮箱/用户名
     * @return
     */
    @ApiOperation("登录获取验证码")
    @RequestMapping(value = "/login/code", method = RequestMethod.GET)
    public CommonResult getLoginCode(@RequestParam String name) {
        return CommonResult.success(userService.getLoginCode(name));
    }
}
