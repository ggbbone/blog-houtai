package com.yzg.blog.portal.service;

import com.yzg.blog.model.BmsArticle;

import java.util.List;

/**
 * Created by yzg on 2019/12/19
 */
public interface BmsArticleService {

    List<BmsArticle> list(int pageNum, int pageSize);

    BmsArticle getById(int id);
}
