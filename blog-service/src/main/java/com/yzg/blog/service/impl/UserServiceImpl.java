package com.yzg.blog.service.impl;

import com.yzg.blog.mapper.UmsUserMapper;
import com.yzg.blog.model.UmsUser;
import com.yzg.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yangzg
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UmsUserMapper userMapper;

    @Override
    public UmsUser getUserById(Integer id) {

        return userMapper.selectByPrimaryKey(id);
    }
}
