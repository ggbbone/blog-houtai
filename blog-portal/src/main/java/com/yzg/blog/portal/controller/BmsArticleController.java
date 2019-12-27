package com.yzg.blog.portal.controller;

import com.yzg.blog.common.api.CommonPage;
import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.model.BmsArticle;
import com.yzg.blog.portal.annotation.LoginToken;
import com.yzg.blog.portal.dto.BmsArticleListParams;
import com.yzg.blog.portal.dto.BmsArticleUpdateParams;
import com.yzg.blog.portal.service.BmsArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by yzg on 2019/12/19
 * 用户文章管理
 */
@RestController
@RequestMapping(value = "article")
@Api(tags = "博文模块文章管理")
public class BmsArticleController {
    private final BmsArticleService bmsArticleService;

    public BmsArticleController(BmsArticleService bmsArticleService) {
        this.bmsArticleService = bmsArticleService;
    }

    @ApiOperation("分页查询所有文章")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage> list(
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @Valid BmsArticleListParams params) {
        List<BmsArticle> list = bmsArticleService.list(pageNum, pageSize, params);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("根据文章id查询文章详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public CommonResult getItemById(@PathVariable int id) {
        BmsArticle article = bmsArticleService.getById(id);
        return CommonResult.success(article);

    }

    @LoginToken
    @ApiOperation("修改文章信息")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public CommonResult update(@Valid @RequestBody BmsArticleUpdateParams params) {
        bmsArticleService.update(params);
        return CommonResult.success(null);
    }

    @LoginToken
    @ApiOperation("删除文章")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public CommonResult delete(@PathVariable int id) {
        bmsArticleService.delete(id);
        return CommonResult.success(null);
    }
}
