package com.yzg.blog.portal.service;

import com.yzg.blog.model.BmsArticleDraft;
import com.yzg.blog.portal.controller.dto.ArticleDraftCreateDTO;
import com.yzg.blog.portal.controller.dto.ArticleDraftUpdateDTO;

import java.util.List;

/**
 * Created by yzg on 2020/1/3
 */
public interface ArticleDraftService {


    /**
     * 创建草稿
     * @param params
     * @return 生成的草稿id
     */
    Integer insert(ArticleDraftCreateDTO params);

    int update(ArticleDraftUpdateDTO params);

    int delete(Integer id);

    List<BmsArticleDraft> list(int pageNum, int pageSize);

    BmsArticleDraft getById(int draftId);
}
