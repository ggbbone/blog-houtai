package com.yzg.blog.portal.controller;

import com.yzg.blog.common.api.CommonPage;
import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.model.CmsComment;
import com.yzg.blog.portal.common.exception.ValidateFailedException;
import com.yzg.blog.portal.config.LoginRole;
import com.yzg.blog.portal.controller.dto.CommentCreateDTO;
import com.yzg.blog.portal.controller.dto.CommentListDTO;
import com.yzg.blog.portal.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Api(tags = "评论模块评论管理")
@Slf4j
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 分页查询 评论/回复
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @param params 查询参数
     * @return
     */
    @ApiOperation("分页查询评论/回复")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage> list(@RequestParam(defaultValue = "1") Integer pageNum,
                                         @RequestParam(defaultValue = "10") Integer pageSize,
                                         @Valid CommentListDTO params) {
        List<CmsComment> list = commentService.list(pageNum, pageSize, params);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 添加 评论
     * @param params
     * @return
     */
    @LoginRole
    @ApiOperation("添加评论/回复")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult add(@Valid @RequestBody CommentCreateDTO params) throws ValidateFailedException {
        int result = commentService.add(params);
        return CommonResult.success(result);
    }

    /**
     * 修改 评论内容
     * @param id 评论id
     * @param content 评论内容
     * @return
     */
    @LoginRole
    @ApiOperation("修改评论/回复的内容")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Integer id, @NotEmpty @RequestParam String content) {
        int result = commentService.update(id, content);
        return CommonResult.success(result);
    }

    /**
     * 删除 评论/回复
     * @param id 评论/回复id
     * @return
     */
    @LoginRole
    @ApiOperation("删除评论/回复")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public CommonResult delete(@PathVariable Integer id) {
        int result = commentService.delete(id);
        return CommonResult.success(result);
    }
}
