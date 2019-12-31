package com.yzg.blog.portal.controller;

import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.portal.dto.UmsLoginParams;
import com.yzg.blog.portal.dto.UmsRegisterParams;
import com.yzg.blog.portal.service.UmsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class UmsSSOController {
    @Autowired
    private UmsUserService userService;

    /**
     * 用户登录
     * @param params
     * @param request
     * @return token
     */
    @ApiOperation("用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(@Valid @RequestBody UmsLoginParams params, HttpServletRequest request) throws Exception {
        return CommonResult.success(userService.login(params, request));
    }

    /**
     * 用户注册
     * @param params
     * @return token
     */
    @ApiOperation("用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult register(@Valid @RequestBody UmsRegisterParams params) throws Exception {
        return CommonResult.success(userService.register(params));
    }

}
