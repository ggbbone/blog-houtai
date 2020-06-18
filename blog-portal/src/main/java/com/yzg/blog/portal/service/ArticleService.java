package com.yzg.blog.portal.service;


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


    ArticleInfoVo getArticleInfoById(Integer id);

    List<ArticleInfoVo> getArticleList(ArticleDTO params);
}
