package com.yzg.blog.portal.service.impl;

import com.yzg.blog.mapper.UmsUserInfoMapper;
import com.yzg.blog.mapper.UmsUserMapper;
import com.yzg.blog.model.UmsUser;
import com.yzg.blog.model.UmsUserInfo;
import com.yzg.blog.model.UmsUserInfoExample;
import com.yzg.blog.portal.model.EUserStatus;
import com.yzg.blog.portal.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by yzg on 2020/1/3
 *
 * 用户详细信息service
 */
@Service
@CacheConfig(cacheNames = {"C_USER_INFO"})
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UmsUserInfoMapper userInfoMapper;
    @Autowired
    UmsUserMapper userMapper;

    @Override
    @Cacheable(key = "#id")
    public UmsUserInfo getUserInfoById(Integer id) {
        UmsUser user = userMapper.selectByPrimaryKey(id);
        if (user == null || user.getStatus() != EUserStatus.NORMAL.getCode()) {
            return null;
        }
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public UmsUserInfo getUserInfoByUsername(String username) {
        UmsUserInfoExample example = new UmsUserInfoExample();
        example.createCriteria().andUsernameEqualTo(username);
        if (userInfoMapper.selectByExample(example).size() == 0) {
            return null;
        } else {
            return userInfoMapper.selectByExample(example).get(0);
        }
    }


}
