package com.yzg.blog.portal.controller;

import com.github.pagehelper.PageHelper;
import com.yzg.blog.common.api.CommonPage;
import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.common.exception.BizException;
import com.yzg.blog.dao.mbg.model.BmsCategory;
import com.yzg.blog.portal.annotation.EnableMethodSecurity;
import com.yzg.blog.portal.annotation.validation.groups.Insert;
import com.yzg.blog.portal.annotation.validation.groups.Select;
import com.yzg.blog.portal.controller.dto.CategoryDTO;
import com.yzg.blog.portal.interceptor.ThreadUser;
import com.yzg.blog.portal.service.CategoryService;
import com.yzg.blog.portal.service.TagService;
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
@RequestMapping(value = "/tags")
@Api(tags = "标签管理")
@Slf4j
public class TagController {
    @Resource
    CategoryService categoryService;
    @Resource
    TagService tagService;

    @ApiOperation("添加标签")
    @EnableMethodSecurity
    @RequestMapping(value = "", method = RequestMethod.POST)
    public CommonResult addTag(@RequestBody @Validated(Insert.class) CategoryDTO dto) throws BizException {
        Integer userId = ThreadUser.get();
        return CommonResult.success().addData("tagId", tagService.addTag(dto));
    }

    @ApiOperation("查询标签列表")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public CommonResult getTags(@Validated(Select.class) CategoryDTO dto,
                                @RequestParam(required = false, defaultValue = "1") Integer page,
                                @Max(100) @RequestParam(required = false, defaultValue = "10") Integer size) throws BizException {

        PageHelper.startPage(page,size);
        List<BmsCategory> tags = tagService.getTagsList(dto);
        return CommonResult.success().addPageData(CommonPage.restPage(tags));
    }

}
