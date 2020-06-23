package com.yzg.blog.portal.controller;

import com.github.pagehelper.PageHelper;
import com.yzg.blog.common.api.CommonPage;
import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.common.exception.BizException;
import com.yzg.blog.dao.mbg.model.BmsCategory;
import com.yzg.blog.portal.annotation.EnableMethodSecurity;
import com.yzg.blog.portal.annotation.validation.groups.Insert;
import com.yzg.blog.portal.annotation.validation.groups.Update;
import com.yzg.blog.portal.controller.dto.ArticleDTO;
import com.yzg.blog.portal.controller.dto.CategoryDTO;
import com.yzg.blog.portal.controller.vo.ArticleInfoVo;
import com.yzg.blog.portal.interceptor.ThreadUser;
import com.yzg.blog.portal.service.ArticleService;
import com.yzg.blog.portal.service.CategoryService;
import com.yzg.blog.portal.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
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
@RequestMapping(value = "/category")
@Api(tags = "分类管理")
@Slf4j
public class CategoryController {
    @Resource
    CategoryService categoryService;

    @ApiOperation("添加分类")
    @EnableMethodSecurity
    @RequestMapping(value = "", method = RequestMethod.POST)
    public CommonResult addCategory(@RequestBody @Validated CategoryDTO dto) throws BizException {
        Integer userId = ThreadUser.get();
        categoryService.addCategory(dto);
        return CommonResult.success();
    }

    @ApiOperation("查询所有分类")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public CommonResult getAllCategory() throws BizException {
        List<BmsCategory> categories = categoryService.getAllCategory();
        return CommonResult.success().addPageData(CommonPage.restPage(categories));
    }

}
