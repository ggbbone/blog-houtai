package com.yzg.blog.portal.service.impl;

import com.github.pagehelper.PageHelper;
import com.yzg.blog.mapper.BmsArticleMapper;
import com.yzg.blog.model.BmsArticle;
import com.yzg.blog.model.BmsArticleExample;
import com.yzg.blog.portal.dao.BmsArticleInfoDao;
import com.yzg.blog.portal.controller.dto.BmsArticleCreateParams;
import com.yzg.blog.portal.controller.dto.BmsArticleListParams;
import com.yzg.blog.portal.controller.dto.BmsArticleUpdateParams;
import com.yzg.blog.portal.model.BmsArticleInfo;
import com.yzg.blog.portal.model.BmsArticleStatus;
import com.yzg.blog.portal.service.BmsArticleService;
import com.yzg.blog.portal.service.BmsCategoryService;
import com.yzg.blog.portal.service.UmsLikeService;
import com.yzg.blog.portal.service.UmsUserInfoService;
import com.yzg.blog.portal.utils.CurrentUser;
import com.yzg.blog.portal.utils.RedisKeysUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yzg on 2019/12/19
 *
 * 文章信息service
 */
@Service
@Slf4j
@CacheConfig(cacheNames = {"C_ARTICLE"})
public class BmsArticleServiceImpl implements BmsArticleService {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    BmsArticleMapper articleMapper;
    @Autowired
    BmsArticleInfoDao articleInfoDao;
    @Autowired
    BmsCategoryService categoryService;
    @Autowired
    UmsUserInfoService userInfoService;
    @Autowired
    UmsLikeService likeService;

    @Override
    public List<BmsArticleInfo> list(int pageNum, int pageSize, BmsArticleListParams params) {
        if (pageSize > 20) {//一次最多查询20条数据
            pageSize = 20;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<BmsArticleInfo> articleInfos = articleInfoDao.list(params);
        //获取文章其他信息
        for (BmsArticleInfo a : articleInfos) {
            //作者信息
            a.setUser(userInfoService.getUserInfoById(a.getUserId()));
            //分类信息
            a.setCategory(categoryService.select(a.getCategoryId()));
            //标签信息
            a.setTags(categoryService.selectArticleTags(a.getId()));
            if (CurrentUser.get() != null) {
                //当前登录用户是否对这篇文章点赞
                a.setHasLike(likeService.hasLike(a.getId(), (byte) 1));
            }
        }
        return articleInfos;
    }

    @Override
    public void addArticleViewCount(int articleId, int count) {
        //浏览次数 + count
        redisTemplate.opsForValue().increment(RedisKeysUtils.getArticleViewCountKey(articleId), count);
        //添加到待同步队列
        redisTemplate.opsForSet().add(RedisKeysUtils.getSyncArticleViewCount(), String.valueOf(articleId));
    }

    @Override
    @Cacheable(key = "#articleId")
    public BmsArticleInfo getById(int articleId) {
        return articleInfoDao.getById(articleId);
    }

    @Override
    @CacheEvict(key = "#articleId")
    @Transactional
    public int delete(int articleId) {
        int result = 0;

        BmsArticleExample example = new BmsArticleExample();
        example.createCriteria()
                .andStatusEqualTo(BmsArticleStatus.NORMAL.getCode())//只有正常状态的文章可以删除
                .andIdEqualTo(articleId)
                .andUserIdEqualTo(CurrentUser.get().getId());//当前登录用户

        //查询要删除的文章
        List<BmsArticle> articles = articleMapper.selectByExample(example);

        if (articles != null && articles.size() > 0) {
            BmsArticle article = new BmsArticle();
            article.setStatus(BmsArticleStatus.DELETE.getCode());//设置状态为 已删除
            result = articleMapper.updateByExampleSelective(article, example);
            //删除成功
            if (result > 0) {
                article = articles.get(0);
                //获取文章标签和分类的ids
                List<Integer> tagIds = categoryService.getTagIdsByArticleId(article.getId());
                ArrayList<Integer> categories = new ArrayList<>(tagIds);
                categories.add(article.getCategoryId());
                //修改标签和分类的文章数量
                categoryService.updateCategoryEntryCount(categories, -1);
            }
        }

        return result;
    }

    @Override
    @CacheEvict(key = "#params.id")
    @Transactional
    public int update(BmsArticleUpdateParams params) {
        int result = 0;
        //查询更改前的文章
        BmsArticle oldArticle = articleMapper.selectByPrimaryKey(params.getId());

        BmsArticleExample example = new BmsArticleExample();
        example.createCriteria()
                .andStatusNotEqualTo(BmsArticleStatus.DELETE.getCode())//状态为 已删除 的文章不能更新
                .andUserIdEqualTo(CurrentUser.get().getId())
                .andIdEqualTo(params.getId());

        BmsArticle article = new BmsArticle();
        article.setTitle(params.getTitle());
        article.setCategoryId(params.getCategory_id());
        article.setContent(params.getContent());
        article.setUpdatedDate(new Date());//设置更新时间
        article.setCover(params.getCover());
        //更新文章
        result = articleMapper.updateByExampleSelective(article, example);
        //更新文章成功
        if (result > 0) {
            //先删除原来的标签
            categoryService.deleteArticleTags(params.getId());
            //添加新标签
            categoryService.insertArticleTags(params.getId(), params.getTagsId());
            if (!oldArticle.getCategoryId().equals(params.getCategory_id())) {
                //更改分类下的文章数量
                categoryService.updateCategoryEntryCount(oldArticle.getCategoryId(), -1);
                categoryService.updateCategoryEntryCount(params.getCategory_id(), 1);
            }
        }
        return result;
    }

    /**
     * 插入文章数据
     * @param params 发表文章请求参数
     * @return 插入成功的行数
     */
    @Override
    @Transactional
    public int add(BmsArticleCreateParams params) {
        BmsArticle article = new BmsArticle();
        article.setUserId(CurrentUser.get().getId());//当前登录用户为作者
        article.setTitle(params.getTitle());
        article.setOutline(params.getContent());
        article.setContent(params.getContent());
        article.setCover(params.getCover());
        article.setCategoryId(params.getCategory_id());
        Date now = new Date();//获取当前时间
        article.setCreatedDate(now);
        article.setUpdatedDate(now);
        article.setLastCommentTime(now);
        //插入文章数据
        int result = articleMapper.insertSelective(article);
        //文章插入成功
        if (result > 0) {
            //插入标签数据
            categoryService.insertArticleTags(article.getId(), params.getTagsId());
            //分类下的文章数量 + 1
            categoryService.updateCategoryEntryCount(params.getCategory_id(), 1);
        }
        //添加到消息队列
        //rabbitTemplate.convertAndSend("add.article.queue", article);
        return result;
    }


}
