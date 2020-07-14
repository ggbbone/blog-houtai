package com.yzg.blog.portal.service;

import com.yzg.blog.dao.mbg.model.BmsArticleDraft;
import com.yzg.blog.portal.controller.dto.DraftDTO;

import java.util.List;

/**
 * @author yangzg
 */
public interface DraftService {

    /**
     * 查询未发布的草稿详情
     * @param id
     * @return
     */
    BmsArticleDraft getDraftInfoById(Integer id);

    /**
     * 获取未发布的草稿列表
     * @param params
     * @return
     */
    List<BmsArticleDraft> listDraftByParams(DraftDTO params);

    /**
     * 添加未发布草稿
     * @param dto
     * @return
     */
    Integer addDraft(DraftDTO dto);

    /**
     * 更新草稿
     * @param dto dto.id,dto.userId
     * @return
     */
    Integer updateDraftById(DraftDTO dto);

    /**
     * 删除用户未发布的草稿
     * @param id
     * @param userId
     * @return
     */
    Integer deleteDraftByIdAndUserId(Integer id, Integer userId);

    /**
     * 草稿发布绑定文章
     * @param draftId
     * @param userId
     * @param articleId
     */
    void updateDraftInArticle(Integer draftId, Integer userId, Integer articleId);

    /**
     * 获取已发布的文章草稿
     * @param id
     * @return
     */
    BmsArticleDraft getDraftInfoByArticleId(Integer id);
}
