package com.yzg.blog.portal.controller;

import com.github.pagehelper.PageHelper;
import com.yzg.blog.common.api.CommonPage;
import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.common.exception.BizException;
import com.yzg.blog.dao.mbg.model.BmsArticleDraft;
import com.yzg.blog.portal.annotation.EnableMethodSecurity;
import com.yzg.blog.portal.annotation.validation.groups.Insert;
import com.yzg.blog.portal.annotation.validation.groups.Update;
import com.yzg.blog.portal.controller.dto.DraftDTO;
import com.yzg.blog.portal.interceptor.ThreadUser;
import com.yzg.blog.portal.service.DraftService;
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
@RequestMapping(value = "/drafts")
@Api(tags = "草稿")
@Slf4j
public class DraftController {
    @Resource
    DraftService draftService;

    @ApiOperation("获取文章草稿详情")
    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
    @EnableMethodSecurity
    public CommonResult getArticleDraftInfo(@PathVariable Integer id) throws BizException {
        return CommonResult.success().addData("draft", draftService.getDraftInfoByArticleId(id));
    }

    @ApiOperation("获取草稿详情")
    @EnableMethodSecurity
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult getDraftInfo(@PathVariable Integer id) throws BizException {
        return CommonResult.success().addData("draft", draftService.getDraftInfoById(id));
    }

    @ApiOperation("获取草稿列表")
    @EnableMethodSecurity
    @RequestMapping(value = "", method = RequestMethod.GET)
    public CommonResult getDraftList(@RequestParam(required = false, defaultValue = "1") Integer page,
                                       @Max(100) @RequestParam(required = false, defaultValue = "20") Integer size,
                                       @Validated(Select.class) DraftDTO params) throws BizException {
        PageHelper.startPage(page, size);
        Integer userId = ThreadUser.get();
        params.setUserId(userId);
        List<BmsArticleDraft> drafts = draftService.listDraftByParams(params);
        return CommonResult.success().addPageData(CommonPage.restPage(drafts));
    }


    @ApiOperation("添加草稿")
    @EnableMethodSecurity
    @RequestMapping(value = "", method = RequestMethod.POST)
    public CommonResult postDraft(@RequestBody @Validated(Insert.class) DraftDTO dto) throws BizException {
        Integer userId = ThreadUser.get();
        dto.setUserId(userId);
        Integer draftId = draftService.addDraft(dto);
        return CommonResult.success().addData("draftId", draftId);
    }

    @ApiOperation("更新草稿")
    @EnableMethodSecurity
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public CommonResult updateDraft(@RequestBody @Validated(Update.class) DraftDTO dto) throws BizException {
        Integer userId = ThreadUser.get();
        dto.setUserId(userId);
        draftService.updateDraftById(dto);
        return CommonResult.success();
    }

    @ApiOperation("删除草稿")
    @EnableMethodSecurity
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public CommonResult deleteDraft(@PathVariable Integer id) throws BizException {
        Integer userId = ThreadUser.get();
        draftService.deleteDraftByIdAndUserId(id, userId);
        return CommonResult.success();
    }


}
