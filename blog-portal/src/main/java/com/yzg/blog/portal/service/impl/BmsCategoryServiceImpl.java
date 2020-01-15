package com.yzg.blog.portal.service.impl;

import com.yzg.blog.mapper.BmsArticleTagsMapper;
import com.yzg.blog.portal.dao.BmsCategoryDao;
import com.yzg.blog.portal.model.BmsArticleTag;
import com.yzg.blog.portal.service.BmsCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzg on 2020/1/6
 *
 * 文章分类/标签service
 */
@Service
@Slf4j
@CacheConfig(cacheNames = {"C_CATEGORY"})
public class BmsCategoryServiceImpl implements BmsCategoryService {

    @Autowired
    BmsCategoryDao categoryDao;
    @Autowired
    BmsArticleTagsMapper articleTagsMapper;

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"C_ARTICLE_TAGS"}, key = "#articleId")
    public void insertArticleTags(int articleId, List<Integer> tags) {
        if (tags != null && tags.size() > 0) {
            categoryDao.insertTags(tags, articleId);
            //标签的文章数量 + 1
            categoryDao.addCategoryEntryCount(tags, 1);
        }
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"C_ARTICLE_TAGS"}, key = "#articleId")
    public void deleteArticleTags(int articleId) {
        //获取文章的所有标签ids
        List<Integer> tags = categoryDao.getTagsIdByArticleId(articleId);
        if (tags != null && tags.size() > 0) {
            //删除文章的所有标签
            categoryDao.deleteTags(articleId);
            //标签的文章数量 - 1
            categoryDao.lessCategoryEntryCount(tags, 1);
        }

    }

    @Override
    @Cacheable(cacheNames = {"C_ARTICLE_TAGS"}, key = "#articleId")
    public List<BmsArticleTag> selectArticleTags(int articleId) {
        return categoryDao.selectArticleTagsByArticleId(articleId);
    }

    @Cacheable(key = "#categoryId")
    @Override
    public BmsArticleTag select(int categoryId) {
        return categoryDao.getById(categoryId);
    }

    @Override
    @Transactional
    public void updateCategoryEntryCount(Integer id, int count) {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(id);
        if (count >= 0) {
            categoryDao.addCategoryEntryCount(ids, count);
        } else {
            categoryDao.lessCategoryEntryCount(ids, -count);
        }
    }

    @Override
    @Transactional
    public void updateCategoryEntryCount(List<Integer> ids, int count) {
        if (ids != null && ids.size() > 0) {
            if (count >= 0) {
                categoryDao.addCategoryEntryCount(ids, count);
            } else {
                categoryDao.lessCategoryEntryCount(ids, -count);
            }
        }
    }

    @Override
    public List<Integer> getTagIdsByArticleId(int articleId) {
        return categoryDao.getTagsIdByArticleId(articleId);
    }
}
