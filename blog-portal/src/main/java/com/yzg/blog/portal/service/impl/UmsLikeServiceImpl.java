package com.yzg.blog.portal.service.impl;

import com.yzg.blog.portal.dto.UmsLikeCommonParams;
import com.yzg.blog.portal.service.UmsLikeService;
import com.yzg.blog.portal.utils.RedisKeysUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by yzg on 2019/12/25
 */
@Service
public class UmsLikeServiceImpl implements UmsLikeService {
    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public Long like(UmsLikeCommonParams params) {
        //获取redis key
        String key = RedisKeysUtils.getLikeKey(params.getType(), params.getTargetId());
        //点赞用户写入redis
        redisTemplate.opsForSet().add(key, "0");//value为当前登录用户id
        //将key添加到修改队列，便于同步到数据库时遍历
        redisTemplate.opsForSet().add(RedisKeysUtils.getChangeLikeKey(), key);
        //返回点赞总数
        return redisTemplate.opsForSet().size(key);
    }

    @Override
    public Long unlike(UmsLikeCommonParams params) {
        //获取redis key
        String key = RedisKeysUtils.getLikeKey(params.getType(), params.getTargetId());
        //从redis中删除该点赞用户
        redisTemplate.opsForSet().remove(key, "0");//value为当前登录用户id
        //将key添加到修改队列，便于同步到数据库时遍历
        redisTemplate.opsForSet().add(RedisKeysUtils.getChangeLikeKey(), key);
        //返回点赞总数
        return redisTemplate.opsForSet().size(key);
    }

    @Override
    public Boolean hasLike(int targetId, byte type) {
        //获取redis key
        String key = RedisKeysUtils.getLikeKey(type, targetId);
        return redisTemplate.opsForSet().isMember(key, "0");
    }

    @Override
    public Long likes(int targetId, byte type) {
        //获取redis key
        String key = RedisKeysUtils.getLikeKey(type, targetId);
        return redisTemplate.opsForSet().size(key);
    }

}
