package com.yzg.blog.portal.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.yzg.blog.common.exception.BadRequestException;
import com.yzg.blog.common.exception.UnauthorizedException;
import com.yzg.blog.common.utils.TokenUtils;
import com.yzg.blog.dao.mbg.mapper.BmsCategoryMapper;
import com.yzg.blog.dao.mbg.mapper.UmsUserInfoMapper;
import com.yzg.blog.dao.mbg.mapper.UmsUserMapper;
import com.yzg.blog.dao.mbg.model.*;
import com.yzg.blog.portal.controller.dto.CategoryDTO;
import com.yzg.blog.portal.controller.dto.UserDTO;
import com.yzg.blog.portal.dao.CategoryDao;
import com.yzg.blog.portal.service.CategoryService;
import com.yzg.blog.portal.service.TagService;
import com.yzg.blog.portal.service.UserService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author yangzg
 */
@Service
@CacheConfig(cacheNames = "CACHE:CATEGORY")
public class CategoryServiceImpl implements CategoryService {

    @Resource
    BmsCategoryMapper categoryMapper;
    @Resource
    CategoryDao categoryDao;

    @Resource
    TagService tagService;


    @Cacheable(value = "CACHE:CATEGORY", key = "#id")
    @Override
    public BmsCategory getCategoryById(Integer id) {
        BmsCategoryExample example = new BmsCategoryExample();
        example.createCriteria().andIdEqualTo(id);
        List<BmsCategory> categories = categoryMapper.selectByExample(example);
        return categories.stream().findFirst().orElse(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTagsByArticleId(Integer id, List<Integer> tagIds) {
        //解除绑定文章标签
        tagService.deleteTagsByArticleId(id);
        tagService.addArticleTags(tagIds, id);
    }


    @Override
    public List<BmsCategory> getCategoriesByIds(List<Integer> ids) {

        BmsCategoryExample example = new BmsCategoryExample();
        example.createCriteria().andIdIn(ids);
        return categoryMapper.selectByExample(example);
    }

    @Override
    public void addCategory(CategoryDTO dto) {

    }

    @Override
    public List<BmsCategory> getAllCategory() {
        BmsCategoryExample example = new BmsCategoryExample();
        example.createCriteria()
                .andIsCategoryEqualTo(true)
                .andStatusEqualTo(categoryStatus.NORMAL.getStatus());
        return categoryMapper.selectByExample(example);
    }

    public enum categoryStatus {
        //已删除
        DELETE((byte) 0),
        //正常
        NORMAL((byte) 1);

        private final byte status;

        categoryStatus(byte status) {
            this.status = status;
        }

        public byte getStatus() {
            return status;
        }
    }
}
