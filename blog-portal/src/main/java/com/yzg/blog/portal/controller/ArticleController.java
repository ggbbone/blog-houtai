package com.yzg.blog.portal.controller;

import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.portal.common.annotation.Role;
import com.yzg.blog.portal.common.exception.ValidateFailedException;
import com.yzg.blog.portal.controller.dto.ArticleCreateDTO;
import com.yzg.blog.portal.controller.dto.ArticleListDTO;
import com.yzg.blog.portal.controller.dto.ArticleUpdateDTO;
import com.yzg.blog.portal.model.ArticleInfo;
import com.yzg.blog.portal.service.ArticleService;
import com.yzg.blog.portal.service.CategoryService;
import com.yzg.blog.portal.service.LikeService;
import com.yzg.blog.portal.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by yzg on 2019/12/19
 * 用户文章管理
 */
@RestController
@RequestMapping(value = "/article")
@Api(tags = "博文模块文章管理")
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    LikeService likeService;

    @ApiOperation("分页查询所有文章")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult list(
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @Valid ArticleListDTO params) {
        List<ArticleInfo> list = articleService.list(pageNum, pageSize, params);
        return CommonResult.success(list);
    }

    @ApiOperation("根据文章id查询文章详细信息")
    @RequestMapping(value = "/{articleId}",method = RequestMethod.GET)
    public CommonResult getItemById(@PathVariable int articleId) {
        ArticleInfo articleInfo = articleService.getById(articleId);
        if (articleInfo != null) {
            //查询标签信息
            articleInfo.setTags(categoryService.selectArticleTags(articleId));
            articleService.addArticleViewCount(articleId, 1);
        }
        return CommonResult.success(articleInfo);

    }

    @Role
    @ApiOperation("编辑文章")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public CommonResult update(@Valid @RequestBody ArticleUpdateDTO params) {

        return CommonResult.success(articleService.update(params));
    }

    @Role
    @ApiOperation("删除文章")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public CommonResult delete(@PathVariable int id) {
        return CommonResult.success(articleService.delete(id));
    }

    @Role
    @ApiOperation("发表文章")
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public CommonResult add(@Valid @RequestBody ArticleCreateDTO params) throws ValidateFailedException {
        return CommonResult.success(articleService.add(params));
    }

}
