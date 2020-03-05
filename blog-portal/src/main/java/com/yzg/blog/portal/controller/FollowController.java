package com.yzg.blog.portal.controller;

import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yzg on 2020/1/2
 *
 * 用户模块关注功能controller
 */
@Validated
@RestController
@RequestMapping(value = "/user/follow")
@Api(tags = "用户模块关注功能")
public class FollowController {

}
