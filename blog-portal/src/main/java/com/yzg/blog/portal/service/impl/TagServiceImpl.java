package com.yzg.blog.portal.service.impl;

import com.yzg.blog.common.exception.BizException;
import com.yzg.blog.common.utils.BeanCopyUtils;
import com.yzg.blog.dao.mbg.mapper.BmsArticleTagsMapper;
import com.yzg.blog.dao.mbg.mapper.BmsCategoryMapper;
import com.yzg.blog.dao.mbg.model.BmsArticleTags;
import com.yzg.blog.dao.mbg.model.BmsArticleTagsExample;
import com.yzg.blog.dao.mbg.model.BmsCategory;
import com.yzg.blog.dao.mbg.model.BmsCategoryExample;
import com.yzg.blog.portal.controller.dto.CategoryDTO;
import com.yzg.blog.portal.dao.CategoryDao;
import com.yzg.blog.portal.dao.TagDao;
import com.yzg.blog.portal.service.TagService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
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
    @Resource
    TagDao tagDao;

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
        BmsArticleTags articleTags = new BmsArticleTags();
        BmsCategoryExample example = new BmsCategoryExample();
        example.createCriteria()
                .andIdIn(tagIds)
                .andStatusEqualTo(CategoryServiceImpl.categoryStatus.NORMAL.getStatus());
        List<BmsCategory> categories = categoryMapper.selectByExample(example);
        if (categories.size() == 0 ) {
            throw BizException.createInstance(-1, "标签不存在");
        }
        categories.forEach(tag -> {
            articleTags.setArticleId(id);
            articleTags.setCategoryId(tag.getId());
            tagsMapper.insert(articleTags);
        });
        //添加标签下的文章数量
        tagDao.addEntryCountByTagIds(tagIds);
    }

    @Override
    public void addTag(CategoryDTO dto) {
        dto.setId(null);
        BmsCategory tag = new BmsCategory();
        BeanCopyUtils.copy(dto, tag);
        tag.setIsCategory(false);
        categoryMapper.insertSelective(tag);
    }

    @Override
    public List<BmsCategory> getTagsList(CategoryDTO dto) {
        if (StringUtils.isNotBlank(dto.getTitle())) {
            dto.setTitle("%" + dto.getTitle() + "%");
        }
        if (StringUtils.isNotBlank(dto.getAlias())) {
            dto.setAlias("%" + dto.getAlias() + "%");
        }
        dto.setOrderBy(TagsSort.getOderByCode(dto.getSort()));
        return tagDao.getTags(dto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "CACHE:ARTICLE_TAGS", key = "#id")
    public void deleteTagsByArticleId(List<Integer> tagIds, Integer id) {
        BmsArticleTagsExample example = new BmsArticleTagsExample();
        example.createCriteria().andArticleIdEqualTo(id);
        tagsMapper.deleteByExample(example);
        tagDao.lessEntryCountByTagIds(tagIds);
    }

    @Override
    public List<Integer> getArticleIdsByTagId(Integer tagId) {
        BmsArticleTagsExample example = new BmsArticleTagsExample();
        example.createCriteria().andCategoryIdEqualTo(tagId);
        List<BmsArticleTags> bmsArticleTags = tagsMapper.selectByExample(example);
        return bmsArticleTags.stream().map(BmsArticleTags::getCategoryId).collect(Collectors.toList());
    }


    public enum TagsSort {
        //默认
        ID( 0,"id desc"),
        ENTRY_COUNT( 1,"entry_count desc"),
        FOLLOW_COUNT( 2,"follow_count desc");

        private final int code;
        private final String orderBy;

        public static String getOderByCode(Integer code) {
            if (code == null) {
                return ID.getOrderBy();
            }
            for (TagsSort g : values()) {
                if (g.getCode() == code) {
                    return g.getOrderBy();
                }
            }
            return ID.getOrderBy();
        }
        TagsSort(int code, String desc) {
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
