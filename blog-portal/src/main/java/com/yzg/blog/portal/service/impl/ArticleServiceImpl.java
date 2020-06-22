package com.yzg.blog.portal.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HtmlUtil;
import com.yzg.blog.common.exception.BadRequestException;
import com.yzg.blog.common.exception.BizException;
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
import com.yzg.blog.portal.dao.ArticleDao;
import com.yzg.blog.portal.service.ArticleService;
import com.yzg.blog.portal.service.CategoryService;
import com.yzg.blog.portal.service.TagService;
import com.yzg.blog.portal.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
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
@Slf4j
@CacheConfig(cacheNames = "CACHE:ARTICLE")
public class ArticleServiceImpl implements ArticleService {

    @Resource
    BmsArticleMapper articleMapper;
    @Resource
    ArticleDao articleDao;
    @Resource
    UserService userService;
    @Resource
    CategoryService categoryService;
    @Resource
    TagService tagService;

    @Override
    @Cacheable(value = "CACHE:ARTICLE_INFO", key = "#id")
    public ArticleInfoVo getArticleInfoById(Integer id) {
        BmsArticle bmsArticle = getArticleById(id);
        if (bmsArticle == null) {
            return null;
        }
        if (ArticleStatus.DELETE.getStatus() == bmsArticle.getStatus()) {
            throw BizException.createInstance(-1, "文章已删除");
        }
        ArticleInfoVo articleInfoVo = new ArticleInfoVo();
        BeanCopyUtils.copy(bmsArticle, articleInfoVo);
        setArticleExtInfo(articleInfoVo);
        return articleInfoVo;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "CACHE:ARTICLE_INFO", key = "#dto.getId()")
    public void updateArticleByIdAndUserId(ArticleDTO dto) {

        BmsArticleExample example = new BmsArticleExample();
        example.createCriteria().andIdEqualTo(dto.getId()).andUserIdEqualTo(dto.getUserId());
        BmsArticle article = new BmsArticle();
        BeanCopyUtils.copy(dto, article);
        article.setContent(StrUtil.subPre(HtmlUtil.cleanHtmlTag(article.getHtml()), 200));
        int result = articleMapper.updateByExampleSelective(article, example);
        if (result > 0) {
            //更新文章标签
            categoryService.updateTagsByArticleId(dto.getId(), dto.getTagIds());

        }
    }

    @Override
    @CacheEvict(value = "CACHE:ARTICLE_INFO", key = "#id")
    public void deleteArticleByIdAndUserId(Integer id, Integer userId) {
        BmsArticleExample example = new BmsArticleExample();
        example.createCriteria().andIdEqualTo(id).andUserIdEqualTo(userId);
        BmsArticle article = new BmsArticle();
        article.setStatus(ArticleStatus.DELETE.getStatus());
        articleMapper.updateByExampleSelective(article, example);
    }
    @Override
    public List<ArticleInfoVo> getArticleList(ArticleDTO params) {
        long start = System.currentTimeMillis();
        List<ArticleInfoVo> articleInfoVos = articleDao.selectArticleList(params);
        log.info("获取文章列表耗时：{}", System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        articleInfoVos.forEach(this::setArticleExtInfo);
        log.info("获取文章详细信息耗时：{}", System.currentTimeMillis() - start);

        return articleInfoVos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BmsArticle addArticle(ArticleDTO dto) {
        BmsArticle article = new BmsArticle();
        BeanCopyUtils.copy(dto, article);
        article.setContent(StrUtil.subPre(HtmlUtil.cleanHtmlTag(article.getHtml()), 200));
        articleMapper.insertSelective(article);
        //添加文章标签
        tagService.addArticleTags(dto.getTagIds(), article.getId());
        return article;
    }

    @Override
    public BmsArticle getArticleById(Integer id) {
        BmsArticle article = articleMapper.selectByPrimaryKey(id);
        if (article == null) {
            throw BizException.createInstance(-1, "文章不存在");
        }
        return article;
    }

    private void setArticleExtInfo(ArticleInfoVo vo) {
        //获取文章分类
        vo.setCategory(categoryService.getCategoryById(vo.getCategoryId()));
        //获取文章标签
        vo.setTags(tagService.getTagsByArticleId(vo.getId()));
        //获取作者信息
        vo.setUserInfo(userService.getUserInfoById(vo.getUserId()));
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
