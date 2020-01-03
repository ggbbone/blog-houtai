package com.yzg.blog.portal.service.impl;

import com.yzg.blog.mapper.UmsUserInfoOtherMapper;
import com.yzg.blog.model.UmsUserInfoOther;
import com.yzg.blog.model.UmsUserInfoOtherExample;
import com.yzg.blog.portal.service.UmsUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yzg on 2020/1/3
 */
@Service
@CacheConfig(cacheNames = {"CACHE::USER_INFO"})
public class UmsUserInfoServiceImpl implements UmsUserInfoService {
    @Autowired
    UmsUserInfoOtherMapper userInfoOtherMapper;

    @Override
    @Cacheable(key = "#id")
    public UmsUserInfoOther getUserInfoById(Integer id) {
        UmsUserInfoOther userInfoOther = new UmsUserInfoOther();
        UmsUserInfoOtherExample example = new UmsUserInfoOtherExample();
        example.createCriteria().andUserIdEqualTo(id);
        List<UmsUserInfoOther> umsUserInfoOthers = userInfoOtherMapper.selectByExample(example);
        if (umsUserInfoOthers != null && umsUserInfoOthers.size() > 0) {
            userInfoOther = umsUserInfoOthers.get(0);
        }
        return userInfoOther;
    }
}
