package com.yzg.blog.portal.service;

import com.yzg.blog.model.BmsArticleDraft;
import com.yzg.blog.portal.dto.BmsArticleDraftAddParams;

import java.util.List;

/**
 * Created by yzg on 2020/1/3
 */
public interface BmsArticleDraftService {


    /**
     * 创建草稿
     * @param params
     * @return 生成的草稿id
     */
    Integer insert(BmsArticleDraftAddParams params);

    int update(BmsArticleDraftAddParams params);

    int delete(Integer id);

    List<BmsArticleDraft> list(int pageNum, int pageSize);
}
