package com.yzg.blog.portal.service;


import com.yzg.blog.dao.mbg.model.BmsArticle;
import com.yzg.blog.dao.mbg.model.UmsUser;
import com.yzg.blog.dao.mbg.model.UmsUserInfo;
import com.yzg.blog.portal.controller.dto.ArticleDTO;
import com.yzg.blog.portal.controller.dto.UserDTO;
import com.yzg.blog.portal.controller.vo.ArticleInfoVo;

import java.util.List;

/**
 * @author yangzg
 */
public interface ArticleService {


    /**
     * 获取文章详情
     * @param id
     * @return
     */
    ArticleInfoVo getArticleInfoById(Integer id);

    /**
     * 查询文章列表
     * @param params
     * @return
     */
    List<ArticleInfoVo> getArticleList(ArticleDTO params);

    /**
     * 添加文章
     * @param dto
     * @return
     */
    BmsArticle addArticle(ArticleDTO dto);

    /**
     * 获取文章
     * @param id
     * @return
     */
    BmsArticle getArticleById(Integer id);

    /**
     * 更新文章信息
     * @param dto
     * @return
     */
    void updateArticleByIdAndUserId(ArticleDTO dto);

    /**
     * 删除文章
     * @param id
     * @param userId
     */
    void deleteArticleByIdAndUserId(Integer id, Integer userId);

    /**
     * 文章阅读数量 + 1
     * @param id
     */
    void incrementViewCount(Integer id);
}
