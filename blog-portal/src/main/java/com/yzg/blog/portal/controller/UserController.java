package com.yzg.blog.portal.controller;

import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.portal.controller.dto.LoginParams;
import com.yzg.blog.portal.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by yzg on 2019/12/27
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
    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public CommonResult getCurrentUserInfo(@RequestBody @Valid LoginParams params) {

        return CommonResult.success();
    }

    @ApiOperation("获取用户详细信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult getUserInfo(@PathVariable Integer id) {
        return CommonResult.success(userService.getUserById(id));
    }

}
