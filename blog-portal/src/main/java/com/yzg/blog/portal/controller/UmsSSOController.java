package com.yzg.blog.portal.controller;

import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.portal.dto.UmsLoginParams;
import com.yzg.blog.portal.dto.UmsRegisterParams;
import com.yzg.blog.portal.service.UmsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 用户模块注册登录管理controller
 * Created by yzg on 2019/12/24
 */
@Validated
@RestController
@RequestMapping(value = "/sso")
@Api(tags = "用户模块登陆注册管理")
public class UmsSSOController {
    @Autowired
    private UmsUserService userService;

    /**
     * 登录获取验证码
     * @param name 邮箱/用户名
     * @return
     */
    @ApiOperation("获取验证码")
    @RequestMapping(value = "/login/code", method = RequestMethod.GET)
    public CommonResult getLoginCode(@RequestParam String name) {
        return CommonResult.success(userService.getLoginCode(name));
    }

    /**
     * 用户登录
     * @param params
     * @param request
     * @return
     */
    @ApiOperation("用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(@Valid @RequestBody UmsLoginParams params, HttpServletRequest request) {
        return CommonResult.success(userService.login(params, request));
    }

    /**
     * 用户注册
     * @param params
     * @return
     */
    @ApiOperation("用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult register(@Valid @RequestBody UmsRegisterParams params) {
        return CommonResult.success(userService.register(params));
    }

}
