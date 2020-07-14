package com.yzg.blog.portal.service.impl;


import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import com.yzg.blog.common.exception.BizException;
import com.yzg.blog.common.utils.BeanCopyUtils;
import com.yzg.blog.dao.mbg.mapper.BmsArticleMapper;
import com.yzg.blog.dao.mbg.model.*;
import com.yzg.blog.portal.controller.dto.ArticleDTO;
import com.yzg.blog.portal.controller.vo.ArticleInfoVo;
import com.yzg.blog.portal.controller.vo.ArticleTagVo;
import com.yzg.blog.portal.dao.ArticleDao;
import com.yzg.blog.portal.service.*;
import com.yzg.blog.portal.utils.RedisKeysUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
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
    DraftService draftService;
    @Resource
    TagService tagService;
    @Autowired
    StringRedisTemplate redisTemplate;

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
    public Integer updateArticleByIdAndUserId(ArticleDTO dto) {
        BmsArticleExample example = new BmsArticleExample();
        example.createCriteria().andIdEqualTo(dto.getId()).andUserIdEqualTo(dto.getUserId());
        BmsArticle newArticle = new BmsArticle();
        BeanCopyUtils.copy(dto, newArticle);
        newArticle.setContent(StrUtil.subPre(HtmlUtil.cleanHtmlTag(newArticle.getHtml()), 200));
        //更新文章标签
        categoryService.updateTagsByArticleId(dto.getId(), dto.getTagIds());
        articleMapper.updateByExampleSelective(newArticle, example);
        return dto.getId();
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
    public void incrementViewCount(Integer id) {
        redisTemplate.opsForSet().add(RedisKeysUtil.getSyncArticleViewCount(), String.valueOf(id));
        redisTemplate.opsForValue().increment(RedisKeysUtil.getArticleViewCountKey(id));
    }

    @Override
    public List<ArticleInfoVo> getArticleList(ArticleDTO params) {
        params.setOrderBy(ArticleSort.getOderByCode(params.getSort()));
        List<ArticleInfoVo> articleInfoVos = articleDao.selectArticleList(params);
        articleInfoVos.forEach(this::setArticleExtInfo);

        return articleInfoVos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BmsArticle addArticle(ArticleDTO dto) {
        BmsCategory categoryById = categoryService.getCategoryById(dto.getCategoryId());
        if (categoryById == null) {
            throw BizException.createInstance(-1, "分类不存在");
        }
        BmsArticle article = new BmsArticle();
        BeanCopyUtils.copy(dto, article);
        article.setContent(StrUtil.subPre(HtmlUtil.cleanHtmlTag(article.getHtml()), 200));
        articleMapper.insertSelective(article);
        //添加文章标签
        tagService.addArticleTags(dto.getTagIds(), article.getId());
        if (article.getId() != null && dto.getId() != null && dto.getUserId() != null) {
            //更改草稿绑定发布的文章
            draftService.updateDraftInArticle(dto.getId(), dto.getUserId(),article.getId());
        }
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

    /**
     * 获取文章详细信息
     * @param vo
     */
    private void setArticleExtInfo(ArticleInfoVo vo) {
        if (vo == null) {
            return;
        }
        //获取文章分类
        vo.setCategory(new ArticleTagVo(categoryService.getCategoryById(vo.getCategoryId())));
        //获取文章标签
        List<BmsCategory> tagsByArticleId = tagService.getTagsByArticleId(vo.getId());
        List<ArticleTagVo> tags = new ArrayList<>();
        tagsByArticleId.forEach(t -> tags.add(new ArticleTagVo(t)));
        vo.setTags(tags);
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

    public enum ArticleSort {
        //默认
        ID(1, "id desc"),
        VIEW_COUNT(2, "view_count desc"),
        LIKE_COUNT(3, "like_count desc"),
        COMMENT_COUNT(4, "comment_count desc"),
        HOT_INDEX(5, "hot_index desc");

        private final int code;
        private final String orderBy;

        public static String getOderByCode(Integer code) {
            if (code == null) {
                return ID.getOrderBy();
            }
            for (ArticleSort g : values()) {
                if (g.getCode() == code) {
                    return g.getOrderBy();
                }
            }
            return ID.getOrderBy();
        }

        ArticleSort(int code, String desc) {
            this.orderBy = desc;
            this.code = code;
        }

        public String getOrderBy() {
            return orderBy;
        }

        public int getCode() {
            return code;
        }
    }
}
