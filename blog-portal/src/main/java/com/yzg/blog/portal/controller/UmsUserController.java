package com.yzg.blog.portal.controller;

import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.portal.common.annotation.Role;
import com.yzg.blog.portal.service.UmsUserInfoService;
import com.yzg.blog.portal.utils.CurrentUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yzg on 2019/12/27
 */
@Validated
@RestController
@RequestMapping(value = "/user")
@Api(tags = "用户模块用户信息管理")
public class UmsUserController {
    @Autowired
    UmsUserInfoService userInfoService;
    /**
     * 获取当前登录用户信息
     * @return
     */
    @Role
    @ApiOperation("获取当前登录用户详细信息")
    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public CommonResult getCurrentUserInfo() {
        return CommonResult.success(userInfoService.getUserInfoById(CurrentUser.get().getId()));
    }


}
