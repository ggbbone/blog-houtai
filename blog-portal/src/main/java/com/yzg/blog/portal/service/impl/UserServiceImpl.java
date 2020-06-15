package com.yzg.blog.portal.service.impl;

import com.yzg.blog.mapper.UmsUserMapper;
import com.yzg.blog.model.UmsUser;
import com.yzg.blog.portal.service.UserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yangzg
 */
@Service
@CacheConfig(cacheNames = "CACHE_USER")
public class UserServiceImpl implements UserService {
    @Resource
    UmsUserMapper userMapper;

    @Cacheable(value = "CACHE_USER", key = "#id")
    @Override
    public UmsUser getUserById(Integer id) {

        return userMapper.selectByPrimaryKey(id);
    }
}
