package com.yzg.blog.portal.controller;

import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.portal.common.annotation.LoginRole;
import com.yzg.blog.portal.controller.dto.FollowDTO;
import com.yzg.blog.portal.controller.dto.LikeDTO;
import com.yzg.blog.portal.service.FollowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
     * 点赞
     * @param params
     * @return
     */
    @LoginRole
    @ApiOperation("关注")
    @PutMapping("")
    public CommonResult follow(@Valid FollowDTO params) throws Exception {
        log.info("FollowController.follow:" + params.toString());
        return CommonResult.success(followService.follow(params));
    }

    /**
     * 取消点赞
     * @param params
     * @return
     */
    @LoginRole
    @ApiOperation("取消关注")
    @Delete("")
    public CommonResult unFollow(@Valid FollowDTO params) throws Exception {
        log.info("FollowController.unFollow:" + params.toString());
        return CommonResult.success(followService.unFollow(params));
    }
}
