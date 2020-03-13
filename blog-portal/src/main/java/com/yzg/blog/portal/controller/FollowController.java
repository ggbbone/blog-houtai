package com.yzg.blog.portal.controller;

import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.portal.config.LoginRole;
import com.yzg.blog.portal.service.FollowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yzg on 2020/1/2
 *
 * 用户模块关注功能controller
 */
@Validated
@RestController
@RequestMapping(value = "/user/follow")
@Api(tags = "用户模块关注功能")
@Slf4j
public class FollowController {

    @Autowired
    private FollowService followService;

    /**
     * 分页查询用户关注列表
     * @return
     */
    @LoginRole
    @ApiOperation("分页查询关注列表")
    @GetMapping("/followees/{type}")
    public CommonResult listFollowee(@PathVariable Byte type,
                                     @RequestParam Integer userId,
                                     @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) throws Exception {
        return CommonResult.success(followService.listFollowees(userId, type, pageNum, pageSize));
    }

    /**
     * 分页查询用户粉丝列表
     * @return
     */
    @LoginRole
    @ApiOperation("分页查询粉丝列表")
    @GetMapping("/followers/{type}")
    public CommonResult listFollower(@PathVariable Byte type,
                                     @RequestParam Integer userId,
                                     @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) throws Exception {
        return CommonResult.success(followService.listFollowers(userId, type, pageNum, pageSize));
    }

    /**
     * 关注
     * @return
     */
    @LoginRole
    @ApiOperation("关注")
    @PutMapping("/{type}/{targetId}")
    public CommonResult follow(@PathVariable Byte type,@PathVariable Integer targetId) throws Exception {
        return CommonResult.success(followService.follow(type, targetId));
    }

    /**
     * 取消关注
     * @return
     */
    @LoginRole
    @ApiOperation("取消关注")
    @DeleteMapping("/{type}/{targetId}")
    public CommonResult unFollow(@PathVariable Byte type,@PathVariable Integer targetId) throws Exception {
        return CommonResult.success(followService.unFollow(type, targetId));
    }

}
