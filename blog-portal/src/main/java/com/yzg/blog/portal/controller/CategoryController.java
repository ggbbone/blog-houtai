package com.yzg.blog.portal.controller;

import com.yzg.blog.common.api.CommonPage;
import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.model.BmsCategory;
import com.yzg.blog.portal.common.annotation.LoginRole;
import com.yzg.blog.portal.common.exception.ValidateFailedException;
import com.yzg.blog.portal.controller.dto.CategoryCreateDTO;
import com.yzg.blog.portal.controller.dto.CategoryUpdateDTO;
import com.yzg.blog.portal.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    @LoginRole
    @PostMapping(value = "/create")
    public CommonResult create(@Valid @RequestBody CategoryCreateDTO categoryCreateDTO) {
        log.info("CategoryController.create");

        int result = categoryService.create(categoryCreateDTO);
        return CommonResult.success(result);
    }

    @LoginRole
    @ApiOperation("添加文章标签")
    @PostMapping(value = "/tag/create")
    public CommonResult createTag(@Valid @RequestBody CategoryCreateDTO categoryCreateDTO) {
        log.info("CategoryController.createTag");
        int result = categoryService.createTag(categoryCreateDTO);
        return CommonResult.success(result);
    }

    @LoginRole
    @ApiOperation("删除分类/标签")
    @PostMapping(value = "/delete/{id}")
    public CommonResult delete(@PathVariable Integer id) throws ValidateFailedException {
        log.info("CategoryController.delete");
        int result = categoryService.delete(id);
        return CommonResult.success(result);
    }

    @LoginRole
    @ApiOperation("修改分类/标签")
    @PostMapping(value = "/update")
    public CommonResult update(@Valid @RequestBody CategoryUpdateDTO categoryUpdateDTO) throws ValidateFailedException {
        log.info("CategoryController.update");
        int result = categoryService.update(categoryUpdateDTO);
        return CommonResult.success(result);
    }

    @ApiOperation("查询分类/标签列表")
    @GetMapping(value = "/list")
    public CommonResult list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                             @RequestParam(value = "isCategory", defaultValue = "true") boolean isCategory) {
        log.info("CategoryController.list");
        List<BmsCategory> list = categoryService.list(pageNum, pageSize, isCategory);
        return CommonResult.success(CommonPage.restPage(list));
    }

}
