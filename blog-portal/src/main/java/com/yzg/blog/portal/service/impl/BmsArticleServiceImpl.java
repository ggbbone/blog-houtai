package com.yzg.blog.portal.service.impl;

import com.github.pagehelper.PageHelper;
import com.yzg.blog.mapper.BmsArticleMapper;
import com.yzg.blog.model.BmsArticle;
import com.yzg.blog.model.BmsArticleExample;
import com.yzg.blog.portal.dto.BmsArticleListParams;
import com.yzg.blog.portal.dto.BmsArticleUpdateParams;
import com.yzg.blog.portal.service.BmsArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yzg on 2019/12/19
 */
@Service
@CacheConfig(cacheNames = {"CACHE::ARTICLE"})
public class BmsArticleServiceImpl implements BmsArticleService {
    @Autowired
    BmsArticleMapper articleMapper;

    @Override
    public List<BmsArticle> list(int pageNum, int pageSize, BmsArticleListParams params) {
        if (pageSize > 20) {//一次最多查询20条数据
            pageSize = 20;
        }
        BmsArticleExample example = new BmsArticleExample();
        BmsArticleExample.Criteria criteria = example.createCriteria();
        //设置排序方式
        example.setOrderByClause(params.getOrderBy());
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
        return articleMapper.selectByExample(example);
    }

    @Override
    @Cacheable(key = "#id")
    public BmsArticle getById(int id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    @CacheEvict(key = "#id")
    public void delete(int id) {
        BmsArticleExample example = new BmsArticleExample();
        example.createCriteria()
                .andIdEqualTo(id)
                .andUserIdEqualTo(1);//当前登录用户id
        BmsArticle article = new BmsArticle();
        article.setStatus((byte) 2);//修改文章状态为2（1正常， 2已删除， 3已屏蔽）"
        articleMapper.updateByExampleSelective(article, example);
    }

    @Override
    @CacheEvict(key = "#params.id")
    public void update(BmsArticleUpdateParams params) {

    }
}
