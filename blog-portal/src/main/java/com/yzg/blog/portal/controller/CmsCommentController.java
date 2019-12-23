package com.yzg.blog.portal.controller;

import com.yzg.blog.common.api.CommonPage;
import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.model.CmsComment;
import com.yzg.blog.portal.dto.CmsCommentAddParams;
import com.yzg.blog.portal.dto.CmsCommentListParams;
import com.yzg.blog.portal.service.CmsCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Created by yzg on 2019/12/20
 *
 * 评论模块评论管理Controller
 */
@Validated
@RestController
@RequestMapping(value = "/comment")
@Api(tags = "CmsCommentController", description = "评论模块评论管理")
public class CmsCommentController {
    @Autowired
    private CmsCommentService commentService;

    @ApiOperation("分页查询评论/回复")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage> list(@RequestParam(defaultValue = "1") Integer pageNum,
                                         @RequestParam(defaultValue = "10") Integer pageSize,
                                         @Valid CmsCommentListParams params) {
        List<CmsComment> list = commentService.list(pageNum, pageSize, params);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 添加评论
     * @param params
     * @return
     */
    @ApiOperation("添加评论/回复")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult add(@Valid @RequestBody CmsCommentAddParams params) {
        int result = commentService.add(params);
        return CommonResult.success(result);
    }

    /**
     * 修改评论内容
     * @param id 评论id
     * @param content 评论内容
     * @return
     */
    @ApiOperation("修改评论/回复")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Integer id, @NotEmpty @RequestParam String content) {
        int result = commentService.update(id, content);
        return CommonResult.success(result);
    }

    /**
     * 删除评论回复
     * @param id 评论/回复id
     * @return
     */
    @ApiOperation("删除评论/回复")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public CommonResult delete(@PathVariable Integer id) {
        int result = commentService.delete(id);
        return CommonResult.success(result);
    }
}
