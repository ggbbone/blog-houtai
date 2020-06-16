package com.yzg.blog.portal.controller;

import com.yzg.blog.demo.api.CommonResult;
import com.yzg.blog.demo.api.ResultCode;
import com.yzg.blog.portal.controller.dto.LoginParams;
import com.yzg.blog.portal.exception.BizException;
import com.yzg.blog.portal.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

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
    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public CommonResult getCurrentUserInfo(@RequestBody @Valid LoginParams params) throws BizException {
        return CommonResult.success();
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public CommonResult test1(@RequestParam @NotBlank String id) throws BizException {
        if ("1".equals(id)) {
            throw BizException.createInstance(ResultCode.FAILED);
        }
        return CommonResult.success();
    }

    @ApiOperation("获取用户详细信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult getUserInfo(@PathVariable Integer id) throws BizException {
        return CommonResult.success(userService.getUserById(id));
    }

}
