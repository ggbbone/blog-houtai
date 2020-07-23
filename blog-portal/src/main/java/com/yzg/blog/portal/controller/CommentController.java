package com.yzg.blog.portal.controller;

import com.github.pagehelper.PageHelper;
import com.yzg.blog.common.api.CommonPage;
import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.common.exception.BizException;
import com.yzg.blog.portal.security.RequiresRoles;
import com.yzg.blog.portal.annotation.validation.groups.Insert;
import com.yzg.blog.portal.annotation.validation.groups.Update;
import com.yzg.blog.portal.controller.dto.CommentDTO;
import com.yzg.blog.portal.controller.vo.CommentInfoVo;
import com.yzg.blog.portal.interceptor.ThreadUser;
import com.yzg.blog.portal.service.CommentService;
import com.yzg.blog.portal.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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
@RequestMapping(value = "/comment")
@Api(tags = "评论管理")
@Slf4j
public class CommentController {
    @Resource
    CommentService commentService;
    @Resource
    TagService tagService;
    @Autowired
    StringRedisTemplate redisTemplate;


    @ApiOperation("获取评论列表")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public CommonResult getCommentList(@RequestParam(required = false, defaultValue = "1") Integer page,
                                       @Max(100) @RequestParam(required = false, defaultValue = "20") Integer size,
                                       @Validated(Select.class) CommentDTO params) throws BizException {
        PageHelper.startPage(page, size);
        List<CommentInfoVo> commentList = commentService.getCommentList(params);
        return CommonResult.success().addPageData(CommonPage.restPage(commentList));
    }


    @ApiOperation("发表评论")
    @RequiresRoles("user")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public CommonResult postComment(@RequestBody @Validated(Insert.class) CommentDTO dto) throws BizException {
        dto.setUserId(ThreadUser.get());
        int commentId = commentService.addComment(dto);
        return CommonResult.success();
    }

    @ApiOperation("更新评论")
    @RequiresRoles("user")
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public CommonResult updateComment(@RequestBody @Validated(Update.class) CommentDTO dto) throws BizException {
        dto.setUserId(ThreadUser.get());
        Integer CommentId = commentService.updateCommentByIdAndUserId(dto);
        return CommonResult.success().addData("commentId", CommentId);
    }

    @ApiOperation("删除评论")
    @RequiresRoles("user")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public CommonResult deleteComment(@PathVariable Integer id) throws BizException {
        Integer userId = ThreadUser.get();
        commentService.deleteCommentByIdAndUserId(id, userId);
        return CommonResult.success();
    }


}
