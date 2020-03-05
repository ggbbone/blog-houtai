package com.yzg.blog.portal.controller;

import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.portal.controller.dto.CategoryCreateDTO;
import com.yzg.blog.portal.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by yzg on 2020/3/5
 */
@RestController
@RequestMapping(value = "/category")
@Slf4j
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @ApiOperation("添加文章分类")
    @PostMapping(value = "/create")
    public CommonResult create(@Valid @RequestBody CategoryCreateDTO categoryCreateDTO) {
        log.info("CategoryController.create");

        int result = categoryService.create(categoryCreateDTO);
        return CommonResult.success(result);
    }

    @ApiOperation("添加文章标签")
    @PostMapping(value = "/tag/create")
    public CommonResult createTag(@Valid @RequestBody CategoryCreateDTO categoryCreateDTO) {
        log.info("CategoryController.create");
        int result = categoryService.createTag(categoryCreateDTO);
        return CommonResult.success(result);
    }
}
