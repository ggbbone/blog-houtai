package com.yzg.blog.portal.service.impl;

import com.github.pagehelper.PageHelper;
import com.yzg.blog.mapper.BmsArticleTagsMapper;
import com.yzg.blog.mapper.BmsCategoryMapper;
import com.yzg.blog.model.BmsCategory;
import com.yzg.blog.model.BmsCategoryExample;
import com.yzg.blog.portal.common.exception.ValidateFailedException;
import com.yzg.blog.portal.controller.dto.CategoryCreateDTO;
import com.yzg.blog.portal.controller.dto.CategoryUpdateDTO;
import com.yzg.blog.portal.dao.BmsCategoryDao;
import com.yzg.blog.portal.model.ArticleTag;
import com.yzg.blog.portal.model.ECategoryStatus;
import com.yzg.blog.portal.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yzg on 2020/1/6
 *
 * 文章分类/标签service
 */
@Service
@Slf4j
@CacheConfig(cacheNames = {"C_CATEGORY"})
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    BmsCategoryDao categoryDao;
    @Autowired
    BmsCategoryMapper categoryMapper;
    @Autowired
    BmsArticleTagsMapper articleTagsMapper;

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"C_ARTICLE_TAGS"}, key = "#articleId")
    public void insertArticleTags(int articleId, List<Integer> tags) {
        if (tags != null && tags.size() > 0) {
            categoryDao.insertTags(tags, articleId);
            //标签下的文章数量 + 1
            categoryDao.addCategoryEntryCount(tags, 1);
        }
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"C_ARTICLE_TAGS"}, key = "#articleId")
    public void deleteArticleTagsByArticleId(int articleId) {
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
    public List<ArticleTag> selectArticleTags(int articleId) {
        return categoryDao.selectArticleTagsByArticleId(articleId);
    }

    @Cacheable(key = "#categoryId")
    @Override
    public ArticleTag select(int categoryId) {
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

    @Override
    public int create(CategoryCreateDTO categoryCreateDTO) {
        BmsCategory category = new BmsCategory();
        BeanUtils.copyProperties(categoryCreateDTO, category);
        category.setCreatedDate(new Date());
        category.setIsCategory(true);
        return categoryMapper.insertSelective(category);
    }

    @Override
    public int createTag(CategoryCreateDTO categoryCreateDTO) {
        BmsCategory category = new BmsCategory();
        BeanUtils.copyProperties(categoryCreateDTO, category);
        category.setCreatedDate(new Date());
        category.setIsCategory(false);
        category.setStatus(ECategoryStatus.LOADING.getCode());
        categoryMapper.insertSelective(category);
        return category.getId();
    }

    @Override
    public BmsCategory getCategoryById(Integer id) {
        BmsCategoryExample example = new BmsCategoryExample();
        example.createCriteria().andStatusEqualTo((byte) 1).andIdEqualTo(id).andIsCategoryEqualTo(true);
        List<BmsCategory> categories = categoryMapper.selectByExample(example);
        if (categories.size() > 0) {
            return categories.get(0);
        }else {
            return null;
        }
    }

    @Override
    public BmsCategory getTagById(Integer tagId) {
        BmsCategoryExample example = new BmsCategoryExample();
        example.createCriteria().andStatusEqualTo((byte) 1).andIdEqualTo(tagId).andIsCategoryEqualTo(false);
        List<BmsCategory> categories = categoryMapper.selectByExample(example);
        if (categories.size() > 0) {
            return categories.get(0);
        }else {
            return null;
        }
    }

    @Override
    public List<BmsCategory> list(int pageNum, int pageSize, boolean isCategory) {
        PageHelper.startPage(pageNum, pageSize);
        BmsCategoryExample example = new BmsCategoryExample();
        example.createCriteria()
                .andIsCategoryEqualTo(isCategory)
                .andStatusEqualTo(ECategoryStatus.NORMAL.getCode());
        return categoryMapper.selectByExample(example);
    }

    @Override
    public int delete(Integer id) throws ValidateFailedException {
        BmsCategory category = categoryMapper.selectByPrimaryKey(id);
        if (category == null) {
            throw new ValidateFailedException("分类/标签不存在");
        }
        category.setStatus(ECategoryStatus.DELETE.getCode());
        return categoryMapper.updateByPrimaryKey(category);
    }

    @Override
    public int update(CategoryUpdateDTO params) throws ValidateFailedException {
        BmsCategory category = categoryMapper.selectByPrimaryKey(params.getId());
        if (category == null) {
            throw new ValidateFailedException("分类/标签不存在");
        }
        BeanUtils.copyProperties(params, category);
        category.setUpdatedDate(new Date());
        return categoryMapper.updateByPrimaryKeySelective(category);
    }
}
