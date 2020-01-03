package com.yzg.blog.portal.service.impl;

import com.github.pagehelper.PageHelper;
import com.yzg.blog.mapper.BmsArticleMapper;
import com.yzg.blog.model.BmsArticle;
import com.yzg.blog.model.BmsArticleExample;
import com.yzg.blog.portal.dao.BmsCategoryDao;
import com.yzg.blog.portal.dto.BmsArticleListParams;
import com.yzg.blog.portal.dto.BmsArticleUpdateParams;
import com.yzg.blog.portal.service.BmsArticleService;
import com.yzg.blog.portal.utils.CurrentUser;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by yzg on 2019/12/19
 */
@Service
@CacheConfig(cacheNames = {"CACHE::ARTICLE"})
public class BmsArticleServiceImpl implements BmsArticleService {
    @Autowired
    BmsArticleMapper articleMapper;
    @Autowired
    BmsCategoryDao bmsCategoryDao;
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public List<BmsArticle> list(int pageNum, int pageSize, BmsArticleListParams params) {
        if (pageSize > 20) {//一次最多查询20条数据
            pageSize = 20;
        }
        BmsArticleExample example = new BmsArticleExample();
        BmsArticleExample.Criteria criteria = example.createCriteria();
        //设置排序方式
        example.setOrderByClause(params.getOrderBy());
        criteria.andStatusEqualTo((byte) 1);//查询状态为1（正常）的文章
        //设置额外查询条件
        if (params.getUserId() != null) {
            criteria.andUserIdEqualTo(params.getUserId());
        }
        if (params.getCategoryId() != null) {
            criteria.andCategoryIdEqualTo(params.getCategoryId());
        }
        if (params.getHot()) {
            criteria.andHotEqualTo(true);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<BmsArticle> articles = articleMapper.selectByExample(example);

        return articles;
    }

    @Override
    @Cacheable(key = "#id")
    public BmsArticle getById(int id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    @CacheEvict(key = "#id")
    public int delete(int id) {
        BmsArticleExample example = new BmsArticleExample();
        example.createCriteria()
                .andIdEqualTo(id)
                .andUserIdEqualTo(CurrentUser.get().getId());//当前登录用户id
        BmsArticle article = new BmsArticle();
        article.setStatus((byte) 2);//修改文章状态为2（1正常， 2已删除， 3已屏蔽）"
        return articleMapper.updateByExampleSelective(article, example);
    }

    @Override
    @CacheEvict(key = "#params.id")
    public int update(BmsArticleUpdateParams params) {
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
        int i = articleMapper.updateByExampleSelective(article, example);
        //更新文章成功时更新标签数据
        if (i > 0) {
            bmsCategoryDao.deleteTags(params.getId());
            List<Integer> tags = params.getTagsId();
            if (tags != null && tags.size() > 0) {
                bmsCategoryDao.insertTags(tags, params.getId());
            }
        }
        return i;
    }

    @Override
    @Transactional
    public int add(BmsArticleUpdateParams params) {
        BmsArticle article = new BmsArticle();
        article.setUserId(CurrentUser.get().getId());//当前登录用户为作者
        article.setTitle(params.getTitle());
        article.setContent(params.getContent());
        article.setCover(params.getCover());
        article.setCreatedDate(new Date());
        article.setUpdatedDate(new Date());
        article.setLastCommentTime(new Date());
        article.setCategoryId(params.getCategory_id());
        //插入文章数据
        int i = articleMapper.insertSelective(article);
        //插入标签数据
        if (i > 0) {
            List<Integer> tags = params.getTagsId();
            if (tags != null && tags.size() > 0) {
                bmsCategoryDao.insertTags(tags, article.getId());
            }
        }
        //添加到消息队列
        //rabbitTemplate.convertAndSend("add.article.queue", article);
        return i;
    }
}
