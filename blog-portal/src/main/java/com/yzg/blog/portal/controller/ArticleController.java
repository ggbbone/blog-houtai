package com.yzg.blog.portal.controller;

import com.github.pagehelper.PageHelper;
import com.yzg.blog.common.api.CommonPage;
import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.common.exception.BizException;
import com.yzg.blog.dao.mbg.model.UmsUserInfo;
import com.yzg.blog.portal.annotation.EnableMethodSecurity;
import com.yzg.blog.portal.annotation.validation.groups.Login;
import com.yzg.blog.portal.annotation.validation.groups.Register;
import com.yzg.blog.portal.controller.dto.ArticleDTO;
import com.yzg.blog.portal.controller.dto.UserDTO;
import com.yzg.blog.portal.controller.dto.UserInfoDTO;
import com.yzg.blog.portal.controller.vo.ArticleInfoVo;
import com.yzg.blog.portal.interceptor.ThreadUser;
import com.yzg.blog.portal.service.ArticleService;
import com.yzg.blog.portal.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Max;
import java.util.List;

/**
 *
 * @author yangzg
 */
@Validated
@RestController
@RequestMapping(value = "/article")
@Api(tags = "文章管理")
@Slf4j
public class ArticleController {
    @Resource
    ArticleService articleService;

    @ApiOperation("获取文章详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult getArticleInfo(@PathVariable Integer id) throws BizException {
        ArticleInfoVo articleInfoById = articleService.getArticleInfoById(id);
        return CommonResult.success().addData("articleInfo", articleInfoById);
    }

    @ApiOperation("获取文章列表")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public CommonResult getArticleList(@RequestParam(required = false, defaultValue = "1") Integer page,
                                       @Max(100) @RequestParam(required = false, defaultValue = "20") Integer size,
                                       ArticleDTO params) throws BizException {
        PageHelper.startPage(page, size);
        List<ArticleInfoVo> articleList = articleService.getArticleList(params);
        return CommonResult.success().addPageData(CommonPage.restPage(articleList));
    }


    @ApiOperation("发表文章")
    @EnableMethodSecurity
    @RequestMapping(value = "", method = RequestMethod.POST)
    public CommonResult postArticle() throws BizException {

        return CommonResult.success();
    }

    @ApiOperation("更新文章")
    @EnableMethodSecurity
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public CommonResult updateArticle(@PathVariable Integer id) throws BizException {

        return CommonResult.success();
    }

    @ApiOperation("删除文章")
    @EnableMethodSecurity
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public CommonResult deleteArticle(@PathVariable Integer id) throws BizException {

        return CommonResult.success();
    }


}