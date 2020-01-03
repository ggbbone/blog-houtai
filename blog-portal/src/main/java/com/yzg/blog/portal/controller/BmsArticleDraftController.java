package com.yzg.blog.portal.controller;

import com.yzg.blog.common.api.CommonPage;
import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.portal.annotation.LoginToken;
import com.yzg.blog.portal.dto.BmsArticleDraftAddParams;
import com.yzg.blog.portal.service.BmsArticleDraftService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by yzg on 2020/1/3
 *
 * 用户文章草稿管理
 */
@RestController
@RequestMapping(value = "article/draft")
@Api(tags = "博文模块草稿管理")
public class BmsArticleDraftController {
    @Autowired
    BmsArticleDraftService articleDraftService;

    @LoginToken
    @ApiOperation("获取草稿列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage> list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return CommonResult.success(CommonPage.restPage(articleDraftService.list(pageNum, pageSize)));
    }

    @LoginToken
    @ApiOperation("创建草稿")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult add(@Valid @RequestBody BmsArticleDraftAddParams params) {
        return CommonResult.success(articleDraftService.insert(params));
    }

    @LoginToken
    @ApiOperation("更新草稿")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult update(@Valid @RequestBody BmsArticleDraftAddParams params) {
        return CommonResult.success(articleDraftService.update(params));
    }

    @LoginToken
    @ApiOperation("删除草稿")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@RequestBody Integer id) {
        return CommonResult.success(articleDraftService.delete(id));
    }
}
