package com.yzg.blog.portal.controller;

import com.yzg.blog.common.api.CommonPage;
import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.portal.config.LoginRole;
import com.yzg.blog.portal.controller.dto.ArticleDraftCreateDTO;
import com.yzg.blog.portal.controller.dto.ArticleDraftUpdateDTO;
import com.yzg.blog.portal.service.ArticleDraftService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by yzg on 2020/1/3
 *
 * 用户文章草稿管理
 */
@RestController
@RequestMapping(value = "/article/draft")
@Api(tags = "博文模块草稿管理")
@Slf4j
public class ArticleDraftController {
    @Autowired
    ArticleDraftService articleDraftService;

    @LoginRole
    @ApiOperation("获取草稿列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage> list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return CommonResult.success(CommonPage.restPage(articleDraftService.list(pageNum, pageSize)));
    }

    @LoginRole
    @ApiOperation("获取草稿详情")
    @RequestMapping(value = "/{draftId}", method = RequestMethod.GET)
    public CommonResult get(@PathVariable int draftId) {
        return CommonResult.success(articleDraftService.getById(draftId));
    }

    @LoginRole
    @ApiOperation("创建草稿")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult add(@Valid @RequestBody ArticleDraftCreateDTO params) {
        return CommonResult.success(articleDraftService.insert(params));
    }

    @LoginRole
    @ApiOperation("更新草稿")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult update(@Valid @RequestBody ArticleDraftUpdateDTO params) {
        return CommonResult.success(articleDraftService.update(params));
    }

    @LoginRole
    @ApiOperation("删除草稿")
    @RequestMapping(value = "/delete/{draftId}", method = RequestMethod.POST)
    public CommonResult delete(@PathVariable Integer draftId) {
        return CommonResult.success(articleDraftService.delete(draftId));
    }
}
