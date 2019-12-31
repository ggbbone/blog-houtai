package com.yzg.blog.portal.service.impl;

import com.yzg.blog.portal.dto.UmsLikeCommonParams;
import com.yzg.blog.portal.exception.ValidateFailedException;
import com.yzg.blog.portal.service.UmsLikeService;
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
public class UmsLikeServiceImpl implements UmsLikeService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public Long like(UmsLikeCommonParams params) throws Exception {
        if (hasLike(params.getTargetId(), params.getType())) {
            throw new ValidateFailedException("不能重复点赞");
        }
        //获取redis key
        String key = RedisKeysUtils.getLikeKey(params.getType(), params.getTargetId());
        //点赞用户写入redis
        redisTemplate.opsForSet().add(key, String.valueOf(CurrentUser.get().getId()));//value为当前登录用户id
        //将key添加到修改队列，便于同步到数据库时遍历
        redisTemplate.opsForSet().add(RedisKeysUtils.getChangeLikeKey(), key);
        //添加到消息队列
        params.setActorId(CurrentUser.get().getId());
        params.setLike(true);
        rabbitTemplate.convertAndSend("like.queue", params);
        //返回点赞总数
        return getLikeCount(params.getTargetId(), params.getType());
    }

    @Override
    public Long unlike(UmsLikeCommonParams params) throws Exception {
        if (!hasLike(params.getTargetId(), params.getType())) {
            throw  new ValidateFailedException("你还没有点赞过");
        }
        //获取redis key
        String key = RedisKeysUtils.getLikeKey(params.getType(), params.getTargetId());
        //从redis中删除当前点赞用户
        redisTemplate.opsForSet().remove(key, String.valueOf(CurrentUser.get().getId()));
        //将key添加到修改队列，便于同步到数据库时遍历
        redisTemplate.opsForSet().add(RedisKeysUtils.getChangeLikeKey(), key);
        //添加到消息队列
        params.setActorId(CurrentUser.get().getId());
        params.setLike(false);
        rabbitTemplate.convertAndSend("like.queue", params);
        //返回点赞总数
        return getLikeCount(params.getTargetId(), params.getType());
    }

    @Override
    public Boolean hasLike(int targetId, byte type) {
        //获取redis key
        String key = RedisKeysUtils.getLikeKey(type, targetId);
        //返回当前用户是否在点赞集合中
        return redisTemplate.opsForSet().isMember(key, String.valueOf(CurrentUser.get().getId()));
    }

    @Override
    public Long getLikeCount(int targetId, byte type) {
        //获取redis key
        String key = RedisKeysUtils.getLikeKey(type, targetId);
        //返回点赞集合中的用户数量
        return redisTemplate.opsForSet().size(key);
    }

}
