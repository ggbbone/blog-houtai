package com.yzg.blog.portal.service.impl;

import com.github.pagehelper.PageHelper;
import com.yzg.blog.mapper.BmsArticleMapper;
import com.yzg.blog.model.BmsArticle;
import com.yzg.blog.model.BmsArticleExample;
import com.yzg.blog.portal.dao.BmsArticleInfoDao;
import com.yzg.blog.portal.dto.BmsArticleCreateParams;
import com.yzg.blog.portal.dto.BmsArticleListParams;
import com.yzg.blog.portal.dto.BmsArticleUpdateParams;
import com.yzg.blog.portal.model.BmsArticleInfo;
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
            a.setCategory(categoryService.selectArticleCategory(a.getCategoryId()));
            //标签信息
            a.setTags(categoryService.selectArticleTags(a.getId()));
            //当前登录用户是否点赞
            if (CurrentUser.get() != null) {
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
        BmsArticleExample example = new BmsArticleExample();
        example.createCriteria()
                .andIdEqualTo(articleId)
                .andUserIdEqualTo(CurrentUser.get().getId());//当前登录用户id
        BmsArticle article = new BmsArticle();
        article.setStatus((byte) 2);//修改文章状态为2（1正常， 2已删除， 3已屏蔽）"
        int i = articleMapper.updateByExampleSelective(article, example);
        if (i > 0) {
            //获取文章标签和分类

            //修改标签和分类的文章数量

        }
        return i;
    }

    @Override
    @CacheEvict(key = "#params.id")
    @Transactional
    public int update(BmsArticleUpdateParams params) {
        //查询更改前的文章
        BmsArticle oldArticle = articleMapper.selectByPrimaryKey(params.getId());

        BmsArticleExample example = new BmsArticleExample();
        example.createCriteria()
                .andUserIdEqualTo(CurrentUser.get().getId())
                .andIdEqualTo(params.getId());

        BmsArticle article = new BmsArticle();
        article.setTitle(params.getTitle());
        article.setCategoryId(params.getCategory_id());
        article.setContent(params.getContent());
        article.setUpdatedDate(new Date());
        article.setCover(params.getCover());
        //更新文章
        int i = articleMapper.updateByExampleSelective(article, example);
        //更新文章成功
        if (i > 0) {
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
        return i;
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
        int i = articleMapper.insertSelective(article);
        //文章插入成功
        if (i > 0) {
            //插入标签数据
            categoryService.insertArticleTags(article.getId(), params.getTagsId());
            //分类下的文章数量 + 1
            categoryService.updateCategoryEntryCount(params.getCategory_id(), 1);
        }
        //添加到消息队列
        //rabbitTemplate.convertAndSend("add.article.queue", article);
        return i;
    }


}
