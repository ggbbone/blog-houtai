package com.yzg.blog.portal.service.impl;

import com.github.pagehelper.PageHelper;
import com.yzg.blog.mapper.BmsArticleDraftMapper;
import com.yzg.blog.model.BmsArticleDraft;
import com.yzg.blog.model.BmsArticleDraftExample;
import com.yzg.blog.portal.controller.dto.BmsArticleDraftAddParams;
import com.yzg.blog.portal.service.BmsArticleDraftService;
import com.yzg.blog.portal.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by yzg on 2020/1/3
 *
 * 用户草稿service
 */
@Service
public class BmsArticleDraftServiceImpl implements BmsArticleDraftService {
    @Autowired
    BmsArticleDraftMapper articleDraftMapper;

    @Override
    public Integer insert(BmsArticleDraftAddParams params) {
        BmsArticleDraft draft = new BmsArticleDraft();
        draft.setUserId(CurrentUser.get().getId());
        if (params.getTitle() == null || "".equals(params.getTitle())) {
            draft.setTitle("无标题");
        } else {
            draft.setTitle(params.getTitle());
        }
        draft.setContent(params.getContent());
        draft.setCreatedDate(new Date());
        draft.setUpdatedDate(new Date());
        draft.setStatus((byte) 1);
        draft.setCover(params.getCover());
        articleDraftMapper.insertSelective(draft);
        return draft.getId();
    }

    @Override
    public int update(BmsArticleDraftAddParams params) {
        BmsArticleDraft draft = new BmsArticleDraft();
        BmsArticleDraftExample example = new BmsArticleDraftExample();
        example.createCriteria()
                .andUserIdEqualTo(CurrentUser.get().getId())
                .andIdEqualTo(params.getId())
                .andStatusEqualTo((byte) 1);
        if (params.getTitle() == null || "".equals(params.getTitle())) {
            draft.setTitle("无标题");
        } else {
            draft.setTitle(params.getTitle());
        }
        draft.setContent(params.getContent());
        draft.setUpdatedDate(new Date());
        draft.setCover(params.getCover());
        return articleDraftMapper.updateByExampleSelective(draft, example);
    }

    @Override
    public int delete(Integer id) {
        BmsArticleDraft draft = new BmsArticleDraft();
        BmsArticleDraftExample example = new BmsArticleDraftExample();
        example.createCriteria()
                .andUserIdEqualTo(CurrentUser.get().getId())
                .andIdEqualTo(id)
                .andStatusEqualTo((byte) 1);
        draft.setStatus((byte) 2);
        draft.setUpdatedDate(new Date());
        return articleDraftMapper.updateByExampleSelective(draft, example);
    }

    @Override
    public List<BmsArticleDraft> list(int pageNum, int pageSize) {
        BmsArticleDraftExample example = new BmsArticleDraftExample();
        example.createCriteria()
                .andUserIdEqualTo(CurrentUser.get().getId())
                .andStatusEqualTo((byte) 1);
        example.setOrderByClause("id desc");
        PageHelper.startPage(pageNum, pageSize);
        return articleDraftMapper.selectByExample(example);
    }
}
