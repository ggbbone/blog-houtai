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
import com.yzg.blog.portal.controller.dto.UserDTO;
import com.yzg.blog.portal.service.CategoryService;
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


    @Override
    public BmsCategory getCategoryById(Integer id) {
        BmsCategoryExample example = new BmsCategoryExample();
        example.createCriteria().andIdEqualTo(id).andIsCategoryEqualTo(true);
        List<BmsCategory> categories = categoryMapper.selectByExample(example);
        return categories.stream().findFirst().orElse(null);
    }
}
