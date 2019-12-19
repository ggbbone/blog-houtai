package com.yzg.blog.portal.controller;

import com.yzg.blog.common.api.CommonPage;
import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.model.BmsArticle;
import com.yzg.blog.portal.service.BmsArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by yzg on 2019/12/19
 * 用户文章管理
 */
@RestController
@RequestMapping(value = "article")
@Api(tags = "BmsArticleController", description = "博文模块文章管理")
public class BmsArticleController {
    @Autowired
    private BmsArticleService bmsArticleService;

    @ApiOperation("分页查询所有文章")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage> list(
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<BmsArticle> list = bmsArticleService.list(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("根据id查询单个文章详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public CommonResult getItemById(@PathVariable(value = "id") int id) {
        BmsArticle article = bmsArticleService.getById(id);
        return CommonResult.success(article);

    }
}
