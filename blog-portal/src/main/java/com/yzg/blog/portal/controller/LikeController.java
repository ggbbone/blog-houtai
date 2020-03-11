package com.yzg.blog.portal.controller;

import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.portal.common.annotation.LoginRole;
import com.yzg.blog.portal.controller.dto.LikeDTO;
import com.yzg.blog.portal.service.LikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@Slf4j
public class LikeController {
    @Autowired
    private LikeService likeService;

    /**
     * 点赞
     * @param params
     * @return
     */
    @LoginRole
    @ApiOperation("点赞")
    @PutMapping("")
    public CommonResult like(@Valid LikeDTO params) throws Exception {
        log.info("LikeController.like:" + params.toString());
        return CommonResult.success(likeService.like(params));
    }

    /**
     * 取消点赞
     * @param params
     * @return
     */
    @LoginRole
    @ApiOperation("取消点赞")
    @DeleteMapping("")
    public CommonResult unlike(@Valid LikeDTO params) throws Exception {
        log.info("LikeController.unlike:" + params.toString());
        return CommonResult.success(likeService.unlike(params));
    }

}
