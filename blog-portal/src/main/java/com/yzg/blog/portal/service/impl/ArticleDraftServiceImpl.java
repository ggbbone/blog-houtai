package com.yzg.blog.portal.service.impl;

import com.github.pagehelper.PageHelper;
import com.yzg.blog.mapper.BmsArticleDraftMapper;
import com.yzg.blog.model.BmsArticleDraft;
import com.yzg.blog.model.BmsArticleDraftExample;
import com.yzg.blog.portal.controller.dto.ArticleDraftCreateDTO;
import com.yzg.blog.portal.controller.dto.ArticleDraftUpdateDTO;
import com.yzg.blog.portal.model.EArticleDraftStatus;
import com.yzg.blog.portal.service.ArticleDraftService;
import com.yzg.blog.portal.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by yzg on 2020/1/3
 *
 * 文章草稿service
 */
@Service
public class ArticleDraftServiceImpl implements ArticleDraftService {
    @Autowired
    BmsArticleDraftMapper articleDraftMapper;

    @Override
    public Integer insert(ArticleDraftCreateDTO params) {
        BmsArticleDraft draft = new BmsArticleDraft();
        draft.setUserId(CurrentUser.get().getId());
        if (params.getTitle() == null || params.getTitle().equals("")) {
            draft.setTitle("无标题");
        } else {
            draft.setTitle(params.getTitle());
        }
        draft.setContent(params.getContent());
        draft.setCover(params.getCover());
        draft.setStatus(EArticleDraftStatus.NORMAL.getCode());
        draft.setCreatedDate(new Date());
        articleDraftMapper.insertSelective(draft);
        return draft.getId();
    }

    @Override
    public int update(ArticleDraftUpdateDTO params) {
        BmsArticleDraft draft = new BmsArticleDraft();
        BmsArticleDraftExample example = new BmsArticleDraftExample();
        example.createCriteria()
                .andUserIdEqualTo(CurrentUser.get().getId())
                .andIdEqualTo(params.getId())
                .andStatusEqualTo(EArticleDraftStatus.NORMAL.getCode());
        if (params.getTitle() == null || params.getTitle().equals("")) {
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
                .andStatusEqualTo(EArticleDraftStatus.NORMAL.getCode());
        draft.setStatus(EArticleDraftStatus.DELETE.getCode());
        draft.setUpdatedDate(new Date());
        return articleDraftMapper.updateByExampleSelective(draft, example);
    }

    @Override
    public List<BmsArticleDraft> list(int pageNum, int pageSize) {
        BmsArticleDraftExample example = new BmsArticleDraftExample();
        example.createCriteria()
                .andUserIdEqualTo(CurrentUser.get().getId())
                .andStatusEqualTo(EArticleDraftStatus.NORMAL.getCode());
        example.setOrderByClause("id desc");
        PageHelper.startPage(pageNum, pageSize);
        return articleDraftMapper.selectByExample(example);
    }

    @Override
    public BmsArticleDraft getById(int draftId) {
        BmsArticleDraftExample example = new BmsArticleDraftExample();
        example.createCriteria()
                .andStatusEqualTo(EArticleDraftStatus.NORMAL.getCode())
                .andIdEqualTo(draftId);
        List<BmsArticleDraft> drafts = articleDraftMapper.selectByExample(example);
        if (drafts.size() > 0) {
            return drafts.get(0);
        }else {
            return null;
        }
    }
}
