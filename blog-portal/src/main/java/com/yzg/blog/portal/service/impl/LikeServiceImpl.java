package com.yzg.blog.portal.service.impl;

import com.yzg.blog.portal.controller.dto.LikeDTO;
import com.yzg.blog.portal.common.exception.ValidateFailedException;
import com.yzg.blog.portal.service.LikeService;
import com.yzg.blog.portal.utils.CurrentUser;
import com.yzg.blog.portal.utils.RedisKeysUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by yzg on 2019/12/25
 */
@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public Long like(LikeDTO params) throws Exception {
        //获取redis key
        String key = RedisKeysUtils.getLikeKey(params.getType(), params.getTargetId());
        //点赞用户写入redis
        redisTemplate.opsForSet().add(key, String.valueOf(CurrentUser.get().getId()));//value为当前登录用户id
        //添加到消息队列
        //rabbitTemplate.convertAndSend("like.queue", params);
        //返回点赞总数
        return getLikeCount(params.getTargetId(), params.getType());
    }

    @Override
    public Long unlike(LikeDTO params) throws Exception {
        //获取redis key
        String key = RedisKeysUtils.getLikeKey(params.getType(), params.getTargetId());
        //从redis中删除当前点赞用户
        redisTemplate.opsForSet().remove(key, String.valueOf(CurrentUser.get().getId()));
        //添加到消息队列
        //rabbitTemplate.convertAndSend("like.queue", params);
        //返回点赞总数
        return getLikeCount(params.getTargetId(), params.getType());
    }

    @Override
    public Boolean hasLike(int targetId, byte type) {
        if (CurrentUser.get() != null) {
            //获取redis key
            String key = RedisKeysUtils.getLikeKey(type, targetId);
            //返回当前用户是否在点赞集合中
            return redisTemplate.opsForSet().isMember(key, String.valueOf(CurrentUser.get().getId()));
        }
        return false;
    }

    @Override
    public Long getLikeCount(int targetId, byte type) {
        //获取redis key
        String key = RedisKeysUtils.getLikeKey(type, targetId);
        //返回点赞集合中的用户数量
        return redisTemplate.opsForSet().size(key);
    }

}
