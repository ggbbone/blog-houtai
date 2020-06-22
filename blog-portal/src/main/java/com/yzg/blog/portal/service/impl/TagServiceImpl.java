package com.yzg.blog.portal.service.impl;

import com.yzg.blog.common.exception.BizException;
import com.yzg.blog.dao.mbg.mapper.BmsArticleTagsMapper;
import com.yzg.blog.dao.mbg.mapper.BmsCategoryMapper;
import com.yzg.blog.dao.mbg.model.BmsArticleTags;
import com.yzg.blog.dao.mbg.model.BmsArticleTagsExample;
import com.yzg.blog.dao.mbg.model.BmsCategory;
import com.yzg.blog.portal.dao.CategoryDao;
import com.yzg.blog.portal.service.TagService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yangzg
 */
@Service
public class TagServiceImpl implements TagService {
    @Resource
    BmsCategoryMapper categoryMapper;
    @Resource
    BmsArticleTagsMapper tagsMapper;
    @Resource
    CategoryDao categoryDao;

    @Cacheable(value = "CACHE:TAG_IDS", key = "#articleId")
    @Override
    public List<Integer> getTagIdsByArticleId(Integer articleId) {
        BmsArticleTagsExample example = new BmsArticleTagsExample();
        example.createCriteria().andArticleIdEqualTo(articleId);
        List<BmsArticleTags> bmsArticleTags = tagsMapper.selectByExample(example);
        return bmsArticleTags.stream().map(BmsArticleTags::getCategoryId).collect(Collectors.toList());
    }

    @Cacheable(value = "CACHE:ARTICLE_TAGS", key = "#id")
    @Override
    public List<BmsCategory> getTagsByArticleId(Integer id) {
        return categoryDao.getTagsByArticleId(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addArticleTags(List<Integer> tagIds, Integer id) {
        BmsArticleTags tags = new BmsArticleTags();
        tagIds.forEach(tagId -> {
            BmsCategory category = categoryMapper.selectByPrimaryKey(tagId);
            if (category == null) {
                throw BizException.createInstance(-1, "标签不存在");
            }
            tags.setArticleId(id);
            tags.setCategoryId(tagId);
            tagsMapper.insert(tags);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "CACHE:ARTICLE_TAGS", key = "#id")
    public void deleteTagsByArticleId(Integer id) {
        BmsArticleTagsExample example = new BmsArticleTagsExample();
        example.createCriteria().andArticleIdEqualTo(id);
        tagsMapper.deleteByExample(example);
    }

    @Override
    public List<Integer> getArticleIdsByTagId(Integer tagId) {
        BmsArticleTagsExample example = new BmsArticleTagsExample();
        example.createCriteria().andCategoryIdEqualTo(tagId);
        List<BmsArticleTags> bmsArticleTags = tagsMapper.selectByExample(example);
        return bmsArticleTags.stream().map(BmsArticleTags::getCategoryId).collect(Collectors.toList());
    }

}
