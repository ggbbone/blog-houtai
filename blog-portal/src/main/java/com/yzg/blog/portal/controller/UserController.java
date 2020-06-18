package com.yzg.blog.portal.controller;

import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.common.exception.BizException;
import com.yzg.blog.dao.mbg.model.UmsUserInfo;
import com.yzg.blog.portal.annotation.EnableMethodSecurity;
import com.yzg.blog.portal.annotation.validation.groups.Login;
import com.yzg.blog.portal.annotation.validation.groups.Register;
import com.yzg.blog.portal.controller.dto.UserDTO;
import com.yzg.blog.portal.controller.dto.UserInfoDTO;
import com.yzg.blog.portal.interceptor.ThreadUser;
import com.yzg.blog.portal.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *
 * @author yangzg
 */
@Validated
@RestController
@RequestMapping(value = "/user")
@Api(tags = "用户管理")
@Slf4j
public class UserController {
    @Resource
    UserService userService;
    /**
     * 获取当前登录用户信息
     * @return
     */
    @ApiOperation("获取当前登录用户详细信息")
    @EnableMethodSecurity
    @RequestMapping(value = "/session", method = RequestMethod.GET)
    public CommonResult getCurrentUserInfo() throws BizException {
        Integer userId = ThreadUser.get();
        UmsUserInfo userInfoById = userService.getUserInfoById(userId);
        return CommonResult.success().addData("userInfo", userInfoById);
    }

    /**
     * 用户登陆
     * @return
     * @throws BizException
     */
    @ApiOperation("用户登陆")
    @RequestMapping(value = "/session", method = RequestMethod.POST)
    public CommonResult login(@RequestBody @Validated(Login.class) UserDTO params) throws BizException {
        String token = userService.login(params);
        return CommonResult.success().addData("token", token);
    }

    /**
     * 注销登陆
     * @return
     * @throws BizException
     */
    @ApiOperation("注销登陆")
    @EnableMethodSecurity
    @RequestMapping(value = "/session", method = RequestMethod.DELETE)
    public CommonResult logout() throws BizException {
        return CommonResult.success();
    }


    @ApiOperation("获取单个用户详细信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult getUserInfo(@PathVariable Integer id) throws BizException {
        UmsUserInfo userInfoById = userService.getUserInfoById(id);
        return CommonResult.success().addData("userInfo", userInfoById);
    }

    @ApiOperation("用户注册")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public CommonResult register(@RequestBody @Validated(Register.class) UserDTO params) {
        String token = userService.register(params);
        return CommonResult.success().addData("token", token);
    }

    @ApiOperation("修改用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.PUT)
    public CommonResult updateUserInfo(@RequestBody UserInfoDTO params) {
        return CommonResult.success();
    }


}
