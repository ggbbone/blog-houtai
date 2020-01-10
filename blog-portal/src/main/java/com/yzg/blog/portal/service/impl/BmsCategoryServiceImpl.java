package com.yzg.blog.portal.service.impl;

import com.yzg.blog.mapper.BmsArticleTagsMapper;
import com.yzg.blog.model.BmsArticleTagsExample;
import com.yzg.blog.portal.dao.BmsCategoryDao;
import com.yzg.blog.portal.model.BmsArticleTag;
import com.yzg.blog.portal.service.BmsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yzg on 2020/1/6
 *
 * 文章分类/标签service
 */
@Service
@CacheConfig(cacheNames = {"C_CATEGORY"})
public class BmsCategoryServiceImpl implements BmsCategoryService {

    @Autowired
    BmsCategoryDao articleTagsDao;
    @Autowired
    BmsArticleTagsMapper articleTagsMapper;

    /**
     * 更新文章的标签
     * @param articleId 文章id
     * @param tags 标签集合
     */
    @Override
    @CacheEvict(cacheNames = {"C_ARTICLE_TAGS"}, key = "#articleId")
    @Transactional
    public void updateArticleTags(int articleId, List<Integer> tags) {
        BmsArticleTagsExample tagsExample = new BmsArticleTagsExample();
        tagsExample.createCriteria().andArticleIdEqualTo(articleId);
        //先删除文章的所有标签
        int i = articleTagsMapper.deleteByExample(tagsExample);
        //重新添加标签
        if (i > 0 && tags != null && tags.size() > 0) {
            articleTagsDao.insertTags(tags, articleId);
        }
    }

    /**
     * 添加文章标签
     * @param articleId 文章id
     * @param tags 标签集合
     */
    @Override
    @CacheEvict(cacheNames = {"C_ARTICLE_TAGS"}, key = "#articleId")
    public void insertArticleTags(int articleId, List<Integer> tags) {
        if (tags != null && tags.size() > 0) {
            articleTagsDao.insertTags(tags, articleId);
        }
    }

    /**
     * 获取文章的标签信息
     * @param articleId 文章id
     */
    @Override
    @Cacheable(cacheNames = {"C_ARTICLE_TAGS"}, key = "#articleId")
    public List<BmsArticleTag> selectArticleTags(int articleId) {
        return articleTagsDao.selectArticleTagsByArticleId(articleId);
    }

    /**
     * 获取分类信息
     * @param categoryId 类别id
     * @return
     */
    @Cacheable(key = "#categoryId")
    @Override
    public BmsArticleTag selectArticleCategory(int categoryId) {
        return articleTagsDao.getById(categoryId);
    }
}
