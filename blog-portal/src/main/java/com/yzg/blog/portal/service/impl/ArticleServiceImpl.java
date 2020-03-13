package com.yzg.blog.portal.service.impl;

import com.github.pagehelper.PageHelper;
import com.yzg.blog.mapper.BmsArticleMapper;
import com.yzg.blog.model.BmsArticle;
import com.yzg.blog.model.BmsArticleExample;
import com.yzg.blog.model.BmsCategory;
import com.yzg.blog.portal.common.exception.ValidateFailedException;
import com.yzg.blog.portal.dao.BmsArticleInfoDao;
import com.yzg.blog.portal.controller.dto.ArticleCreateDTO;
import com.yzg.blog.portal.controller.dto.ArticleListDTO;
import com.yzg.blog.portal.controller.dto.ArticleUpdateDTO;
import com.yzg.blog.portal.model.ArticleInfo;
import com.yzg.blog.portal.model.EArticleStatus;
import com.yzg.blog.portal.model.ELikeType;
import com.yzg.blog.portal.service.ArticleService;
import com.yzg.blog.portal.service.CategoryService;
import com.yzg.blog.portal.service.LikeService;
import com.yzg.blog.portal.service.UserInfoService;
import com.yzg.blog.portal.utils.CurrentUser;
import com.yzg.blog.portal.utils.RedisKeysUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
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
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    BmsArticleMapper articleMapper;
    @Autowired
    BmsArticleInfoDao articleInfoDao;
    @Autowired
    CategoryService categoryService;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    LikeService likeService;

    @Override
    public List<ArticleInfo> list(int pageNum, int pageSize, ArticleListDTO params) {
        if (pageSize > 20) {//一次最多查询20条数据
            pageSize = 20;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleInfo> articleInfos = articleInfoDao.list(params);
        //获取文章其他信息
        for (ArticleInfo a : articleInfos) {
            //作者信息
            a.setUser(userInfoService.getUserInfoById(a.getUserId()));
            //分类信息
            a.setCategory(categoryService.select(a.getCategoryId()));
            //标签信息
            a.setTags(categoryService.selectArticleTags(a.getId()));
            //点赞人数
            a.setLikeCount(Math.toIntExact(likeService.getLikeCount(a.getId(), ELikeType.ARTICLE.getCode())));
            //是否点赞
            a.setHasLike(likeService.hasLike(a.getId(), ELikeType.ARTICLE.getCode()));
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
    public ArticleInfo getById(int articleId) {
        return articleInfoDao.getById(articleId);
    }

    @Override
    @CacheEvict(key = "#articleId")
    @Transactional
    public int delete(int articleId) throws ValidateFailedException {

        int result = 0;
        BmsArticleExample example = new BmsArticleExample();
        example.createCriteria()
                .andStatusEqualTo(EArticleStatus.NORMAL.getCode())//只有正常状态的文章可以删除
                .andIdEqualTo(articleId)
                .andUserIdEqualTo(CurrentUser.get().getId());//当前登录用户

        //查询要删除的文章
        List<BmsArticle> articles = articleMapper.selectByExample(example);
        if (articles.size() == 0) {
            throw new ValidateFailedException("文章不存在");
        }
        BmsArticle article = new BmsArticle();
        article.setStatus(EArticleStatus.DELETE.getCode());//设置状态为 已删除
        result = articleMapper.updateByExampleSelective(article, example);
        //删除成功
        if (result > 0) {
            article = articles.get(0);
            //获取文章标签和分类
            List<Integer> tagIds = categoryService.getTagIdsByArticleId(article.getId());
            ArrayList<Integer> categories = new ArrayList<>(tagIds);
            categories.add(article.getCategoryId());
            //修改标签和分类的文章数量
            categoryService.updateCategoryEntryCount(categories, -1);
        }

        return result;
    }

    @Override
    @CacheEvict(key = "#params.id")
    @Transactional
    public int update(ArticleUpdateDTO params) throws ValidateFailedException {

        //查询更改前的文章
        BmsArticle oldArticle = articleMapper.selectByPrimaryKey(params.getId());
        if (oldArticle == null) {
            throw new ValidateFailedException("文章不存在");
        }
        BmsArticleExample example = new BmsArticleExample();
        example.createCriteria()
                .andStatusNotEqualTo(EArticleStatus.DELETE.getCode())//状态为 已删除 的文章不能更新
                .andUserIdEqualTo(CurrentUser.get().getId())
                .andIdEqualTo(params.getId());

        BmsArticle article = new BmsArticle();
        BeanUtils.copyProperties(params, article);
        article.setUpdatedDate(new Date());//设置更新时间
        //更新文章
        int result = articleMapper.updateByExampleSelective(article, example);
        //更新文章成功
        if (result > 0) {
            //删除文章的标签
            categoryService.deleteArticleTagsByArticleId(params.getId());
            //绑定新标签到文章
            categoryService.insertArticleTags(params.getId(), params.getTagsId());
            if (!oldArticle.getCategoryId().equals(params.getCategoryId())) {
                //更改分类下的文章数量
                categoryService.updateCategoryEntryCount(oldArticle.getCategoryId(), -1);
                categoryService.updateCategoryEntryCount(params.getCategoryId(), 1);
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
    public int add(ArticleCreateDTO params) throws ValidateFailedException {

        //查询分类
        BmsCategory categoryById = categoryService.getCategoryById(params.getCategoryId());
        if (categoryById == null) {
            throw new ValidateFailedException("文章分类不存在");
        }
        //查询标签
        for (Integer tagId : params.getTagsId()) {
            BmsCategory tag = categoryService.getTagById(tagId);
            if (tag == null) {
                throw new ValidateFailedException("文章标签有误");
            }
        }
        BmsArticle article = new BmsArticle();
        BeanUtils.copyProperties(params, article);
        article.setUserId(CurrentUser.get().getId());//当前登录用户为作者
        article.setCreatedDate(new Date());
        //插入文章数据
        int result = articleMapper.insertSelective(article);
        //文章插入成功
        if (result > 0) {
            //插入标签数据
            categoryService.insertArticleTags(article.getId(), params.getTagsId());
            //分类下的文章数量 + 1
            categoryService.updateCategoryEntryCount(params.getCategoryId(), 1);
        }
        //添加到消息队列
        //rabbitTemplate.convertAndSend("add.article.queue", article);
        return result;
    }

    @Override
    public int addArticleCommentCount(Integer parentId, int i) {
        return articleInfoDao.addCommentCount(parentId, i);
    }


}
