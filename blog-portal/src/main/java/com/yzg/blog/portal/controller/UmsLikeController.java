package com.yzg.blog.portal.controller;

import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.portal.annotation.Role;
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
@Api(tags = "用户模块点赞功能")
public class UmsLikeController {
    @Autowired
    private UmsLikeService likeService;

    /**
     * 点赞
     * @param params
     * @return
     */
    @Role
    @ApiOperation("点赞")
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public CommonResult like(@Valid UmsLikeCommonParams params) throws Exception {
        return CommonResult.success(likeService.like(params));
    }

    /**
     * 取消点赞
     * @param params
     * @return
     */
    @Role
    @ApiOperation("取消点赞")
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public CommonResult unlike(@Valid UmsLikeCommonParams params) throws Exception {
        return CommonResult.success(likeService.unlike(params));
    }

}
