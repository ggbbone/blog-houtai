package com.yzg.blog.portal.controller;

import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.portal.annotation.Role;
import com.yzg.blog.portal.dto.BmsArticleCreateParams;
import com.yzg.blog.portal.dto.BmsArticleListParams;
import com.yzg.blog.portal.dto.BmsArticleUpdateParams;
import com.yzg.blog.portal.model.BmsArticleInfo;
import com.yzg.blog.portal.service.BmsArticleService;
import com.yzg.blog.portal.service.BmsCategoryService;
import com.yzg.blog.portal.service.UmsLikeService;
import com.yzg.blog.portal.service.UmsUserInfoService;
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
@RequestMapping(value = "article")
@Api(tags = "博文模块文章管理")
public class BmsArticleController {
    @Autowired
    BmsArticleService bmsArticleService;
    @Autowired
    BmsCategoryService categoryService;
    @Autowired
    UmsUserInfoService userInfoService;
    @Autowired
    UmsLikeService likeService;



    @ApiOperation("分页查询所有文章")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult list(
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @Valid BmsArticleListParams params) {
        List<BmsArticleInfo> list = bmsArticleService.list(pageNum, pageSize, params);
        return CommonResult.success(list);
    }

    @ApiOperation("根据文章id查询文章详细信息")
    @RequestMapping(value = "/{articleId}",method = RequestMethod.GET)
    public CommonResult getItemById(@PathVariable int articleId) {
        BmsArticleInfo articleInfo = bmsArticleService.getById(articleId);
        if (articleInfo != null) {
            //查询标签信息
            articleInfo.setTags(categoryService.selectArticleTags(articleId));
            bmsArticleService.addArticleViewCount(articleId, 1);
        }
        return CommonResult.success(articleInfo);

    }

    @Role
    @ApiOperation("修改文章信息")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public CommonResult update(@Valid @RequestBody BmsArticleUpdateParams params) {

        return CommonResult.success(bmsArticleService.update(params));
    }

    @Role
    @ApiOperation("删除文章")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public CommonResult delete(@PathVariable int id) {
        return CommonResult.success(bmsArticleService.delete(id));
    }

    @Role
    @ApiOperation("发表文章")
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public CommonResult add(@Valid @RequestBody BmsArticleCreateParams params) {
        return CommonResult.success(bmsArticleService.add(params));
    }

}
