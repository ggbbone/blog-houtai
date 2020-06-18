package com.yzg.blog.portal.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.yzg.blog.common.exception.BadRequestException;
import com.yzg.blog.common.exception.UnauthorizedException;
import com.yzg.blog.common.utils.BeanCopyUtils;
import com.yzg.blog.common.utils.TokenUtils;
import com.yzg.blog.dao.mbg.mapper.BmsArticleMapper;
import com.yzg.blog.dao.mbg.mapper.UmsUserInfoMapper;
import com.yzg.blog.dao.mbg.mapper.UmsUserMapper;
import com.yzg.blog.dao.mbg.model.*;
import com.yzg.blog.portal.controller.dto.ArticleDTO;
import com.yzg.blog.portal.controller.dto.UserDTO;
import com.yzg.blog.portal.controller.vo.ArticleInfoVo;
import com.yzg.blog.portal.service.ArticleService;
import com.yzg.blog.portal.service.CategoryService;
import com.yzg.blog.portal.service.UserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangzg
 */
@Service
@CacheConfig(cacheNames = "CACHE:ARTICLE")
public class ArticleServiceImpl implements ArticleService {

    @Resource
    BmsArticleMapper articleMapper;
    @Resource
    UserService userService;
    @Resource
    CategoryService categoryService;

    @Override
    public ArticleInfoVo getArticleInfoById(Integer id) {
        BmsArticle bmsArticle = getArticleById(id);
        if (bmsArticle == null) {
            return null;
        }
        ArticleInfoVo articleInfoVo = new ArticleInfoVo();
        BeanCopyUtils.copy(bmsArticle, articleInfoVo);
        //获取文章分类
        articleInfoVo.setCategory(categoryService.getCategoryById(articleInfoVo.getCategoryId()));
        //获取文章标签

        //获取作者信息
        articleInfoVo.setUserInfo(userService.getUserInfoById(articleInfoVo.getUserId()));

        return articleInfoVo;
    }

    @Override
    public List<ArticleInfoVo> getArticleList(ArticleDTO params) {
        List<ArticleInfoVo> articleInfoVos = new ArrayList<>();
        BmsArticleExample example = new BmsArticleExample();
        example.createCriteria().andStatusEqualTo(ArticleStatus.NORMAL.getStatus());
        List<BmsArticle> bmsArticles = articleMapper.selectByExample(example);
        for (BmsArticle article : bmsArticles) {
            ArticleInfoVo vo = new ArticleInfoVo();
            BeanCopyUtils.copy(article, vo);
            vo.setUserInfo(userService.getUserInfoById(vo.getUserId()));
            articleInfoVos.add(vo);
        }
        return articleInfoVos;
    }

    public BmsArticle getArticleById(Integer id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    public enum ArticleStatus {
        //已删除
        DELETE((byte) 0),
        //正常
        NORMAL((byte) 1);

        private final byte status;

        ArticleStatus(byte status) {
            this.status = status;
        }

        public byte getStatus() {
            return status;
        }
    }
}
