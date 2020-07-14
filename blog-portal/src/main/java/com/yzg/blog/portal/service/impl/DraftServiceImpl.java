package com.yzg.blog.portal.service.impl;

import com.yzg.blog.common.exception.BadRequestException;
import com.yzg.blog.common.utils.BeanCopyUtils;
import com.yzg.blog.dao.mbg.mapper.BmsArticleDraftMapper;
import com.yzg.blog.dao.mbg.mapper.BmsArticleMapper;
import com.yzg.blog.dao.mbg.model.BmsArticle;
import com.yzg.blog.dao.mbg.model.BmsArticleDraft;
import com.yzg.blog.dao.mbg.model.BmsArticleDraftExample;
import com.yzg.blog.dao.mbg.model.BmsArticleExample;
import com.yzg.blog.portal.controller.dto.DraftDTO;
import com.yzg.blog.portal.interceptor.ThreadUser;
import com.yzg.blog.portal.service.DraftService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangzg
 */
@Service
public class DraftServiceImpl implements DraftService {

    @Resource
    BmsArticleDraftMapper draftMapper;
    @Resource
    BmsArticleMapper articleMapper;

    @Override
    public BmsArticleDraft getDraftInfoById(Integer id) {
        BmsArticleDraftExample example = new BmsArticleDraftExample();
        example.createCriteria().andIdEqualTo(id).andStatusNotEqualTo(DraftStatus.DELETE.getStatus());
        List<BmsArticleDraft> drafts = draftMapper.selectByExampleWithBLOBs(example);
        if (drafts != null && drafts.size() > 0) {
            return drafts.get(0);
        }

        return null;
    }

    @Override
    public List<BmsArticleDraft> listDraftByParams(DraftDTO params) {
        BmsArticleDraftExample example = new BmsArticleDraftExample();
        example.setOrderByClause("id desc");
        BmsArticleDraftExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(DraftStatus.NORMAL.getStatus());
        if (params.getUserId() != null) {
            criteria.andUserIdEqualTo(params.getUserId());
        }
        return draftMapper.selectByExample(example);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addDraft(DraftDTO dto) {
        dto.setId(null);
        BmsArticleDraft draft = new BmsArticleDraft();
        BeanCopyUtils.copy(dto, draft);
        if (StringUtils.isBlank(draft.getTitle())) {
            draft.setTitle("无标题");
        }
        draftMapper.insertSelective(draft);
        return draft.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateDraftById(DraftDTO dto) {
        BmsArticleDraft draft = new BmsArticleDraft();
        BeanCopyUtils.copy(dto, draft);
        BmsArticleDraftExample example = new BmsArticleDraftExample();
        example.createCriteria()
                .andIdEqualTo(dto.getId())
                .andUserIdEqualTo(dto.getUserId())
                .andStatusNotEqualTo(DraftStatus.DELETE.getStatus());
        return draftMapper.updateByExampleSelective(draft, example);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteDraftByIdAndUserId(Integer id, Integer userId) {
        BmsArticleDraftExample example = new BmsArticleDraftExample();
        BmsArticleDraftExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id)
                .andUserIdEqualTo(userId)
                .andStatusEqualTo(DraftStatus.NORMAL.getStatus());
        BmsArticleDraft draft = new BmsArticleDraft();
        draft.setStatus(DraftStatus.DELETE.getStatus());
        return draftMapper.updateByExampleSelective(draft, example);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDraftInArticle(Integer draftId, Integer userId, Integer articleId) {
        BmsArticleDraftExample example = new BmsArticleDraftExample();
        example.createCriteria().andIdEqualTo(draftId).andUserIdEqualTo(userId);
        BmsArticleDraft draft = new BmsArticleDraft();
        draft.setArticleId(articleId);
        draft.setStatus(DraftStatus.PUBLISHED.getStatus());
        draftMapper.updateByExampleSelective(draft, example);
    }

    @Override
    public BmsArticleDraft getDraftInfoByArticleId(Integer articleId) {
        BmsArticleDraftExample example = new BmsArticleDraftExample();
        example.setOrderByClause("id desc");
        example.createCriteria().andArticleIdEqualTo(articleId)
                .andStatusEqualTo(DraftStatus.PUBLISHED.getStatus());
        List<BmsArticleDraft> drafts = draftMapper.selectByExampleWithBLOBs(example);
        if (drafts.size() > 0) {
            return drafts.get(0);
        } else {
            //文章没有草稿，新建草稿
            BmsArticleExample articleExample = new BmsArticleExample();
            articleExample.createCriteria()
                    .andIdEqualTo(articleId)
                    .andUserIdEqualTo(ThreadUser.get())
                    .andStatusEqualTo(ArticleServiceImpl.ArticleStatus.NORMAL.getStatus());
            List<BmsArticle> articles = articleMapper.selectByExampleWithBLOBs(articleExample);
            if (articles.size() == 0) {
                throw new BadRequestException("文章不存在");
            }
            BmsArticle article = articles.get(0);
            return draftMapper.selectByPrimaryKey(addArticleDraft(article));
        }

    }

    private Integer addArticleDraft(BmsArticle article) {
        BmsArticleDraft draft = new BmsArticleDraft();
        draft.setStatus(DraftStatus.PUBLISHED.getStatus());
        draft.setArticleId(article.getId());
        draft.setTitle(article.getTitle());
        draft.setContent(article.getMarkdown());
        draft.setCoverUrl(article.getCoverUrl());
        draft.setUserId(article.getUserId());
        draftMapper.insertSelective(draft);
        return draft.getId();
    }

    /**
     * 草稿状态
     */
    public enum DraftStatus {
        //已删除
        DELETE((byte) 0),
        //正常
        NORMAL((byte) 1),
        //已发布
        PUBLISHED((byte) 2);

        private final byte status;

        DraftStatus(byte status) {
            this.status = status;
        }

        public byte getStatus() {
            return status;
        }
    }
}
