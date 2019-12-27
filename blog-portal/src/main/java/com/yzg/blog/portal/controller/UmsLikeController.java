package com.yzg.blog.portal.controller;

import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.portal.annotation.LoginToken;
import com.yzg.blog.portal.dto.UmsLikeCommonParams;
import com.yzg.blog.portal.service.UmsLikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by yzg on 2019/12/25
 *
 * 用户模块点赞功能controller
 */
@Validated
@RestController
@RequestMapping(value = "/user/like")
@Api(tags = "论模块点赞功能")
public class UmsLikeController {
    @Autowired
    private UmsLikeService likeService;

    /**
     * 点赞
     * @param params
     * @return
     */
    @LoginToken
    @ApiOperation("点赞")
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public CommonResult like(@Valid UmsLikeCommonParams params) {
        return CommonResult.success(likeService.like(params));
    }

    /**
     * 取消点赞
     * @param params
     * @return
     */
    @LoginToken
    @ApiOperation("取消点赞")
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public CommonResult unlike(@Valid UmsLikeCommonParams params) {
        return CommonResult.success(likeService.unlike(params));
    }

    /**
     * 查询是否点赞
     * @param params
     * @return
     */
    @LoginToken
    @ApiOperation("查询是否点赞")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public CommonResult hasLike(@Valid UmsLikeCommonParams params) {
        return CommonResult.success(
                likeService.hasLike(params.getTargetId(), params.getType()));
    }
}
