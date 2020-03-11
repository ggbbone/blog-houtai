package com.yzg.blog.portal.service.impl;

import com.yzg.blog.mapper.UmsUserFollowMapper;
import com.yzg.blog.portal.common.component.Message;
import com.yzg.blog.portal.service.FollowService;
import com.yzg.blog.portal.utils.CurrentUser;
import com.yzg.blog.portal.utils.RedisKeysUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by yzg on 2020/1/2
 */
@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    UmsUserFollowMapper userFollowMapper;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public Long follow(Byte type, Integer targetId) {
        //获取目标粉丝列表 key
        String key = RedisKeysUtils.getFollowerKey(type, targetId);
        //用户id写入目标粉丝列表
        redisTemplate.opsForZSet().add(key, String.valueOf(CurrentUser.get().getId()),System.currentTimeMillis());//value为当前登录用户id
        //获取用户关注列表 key
        key = RedisKeysUtils.getFolloweeKey(CurrentUser.get().getId(), type);
        //目标id写入用户关注列表
        redisTemplate.opsForZSet().add(key, String.valueOf(targetId),System.currentTimeMillis());//value为当前登录用户id
        //添加到消息队列
        Message message = Message.createMessage(CurrentUser.get().getId(), type, targetId).add("follow", true);
        rabbitTemplate.convertAndSend("follow.queue", message);
        //返回目标粉丝数量
        return getFollowerCount(type, targetId);
    }

    @Override
    public Long getFollowerCount(Byte type, Integer targetId) {
        //获取redis key
        String key = RedisKeysUtils.getFollowerKey(type, targetId);
        //返回目标粉丝数量
        return redisTemplate.opsForZSet().size(key);
    }

    @Override
    public Long getFolloweeCount(Integer userId, Integer type) {
        //获取redis key
        String key = RedisKeysUtils.getFolloweeKey(userId, type);
        //返回用户关注总数
        return redisTemplate.opsForZSet().size(key);
    }

    @Override
    public Set<String> getFolloweeIdsPage(Integer userId, Byte type, int page, int size) {
        //获取用户关注列表key
        String key = RedisKeysUtils.getFolloweeKey(userId, type);
        //获取用户关注列表
        return redisTemplate.opsForZSet().reverseRange(key, (page - 1) * size, page * size - 1);
    }

    @Override
    public Set<String> getFollowerIdsPage(Integer userId, Byte type, int page, int size) {
        //获取用户粉丝列表key
        String key = RedisKeysUtils.getFollowerKey(userId, type);
        //获取用户粉丝列表
        return redisTemplate.opsForZSet().reverseRange(key, (page - 1) * size, page * size - 1);
    }

    @Override
    public Boolean hasFollow(int targetId, byte type) {
        if (CurrentUser.get() != null) {
            //获取目标粉丝列表 key
            String key = RedisKeysUtils.getFollowerKey(type, targetId);
            //返回当前用户id是否在目标粉丝列表中
            Double rank = redisTemplate.opsForZSet().score(key, String.valueOf(CurrentUser.get().getId()));
            return rank != null;
        }
        return false;
    }

    @Override
    public List<Object> listFollowees(Integer userId, Byte type, int pageNum, int pageSize) {
        Set<String> followeeIds = getFolloweeIdsPage(userId, type, pageNum, pageSize);
        return Collections.singletonList(followeeIds);
    }

    @Override
    public List<Object> listFollowers(Integer userId, Byte type, int pageNum, int pageSize) {
        Set<String> followeeIds = getFollowerIdsPage(userId, type, pageNum, pageSize);
        return Collections.singletonList(followeeIds);
    }

    @Override
    public Long unFollow(Byte type, Integer targetId) {
        //获取目标粉丝列表 key
        String key = RedisKeysUtils.getFollowerKey(type, targetId);
        //从目标粉丝列表中删除当前关注用户id
        redisTemplate.opsForZSet().remove(key, String.valueOf(CurrentUser.get().getId()));
        //获取用户关注列表
        key = RedisKeysUtils.getFolloweeKey(CurrentUser.get().getId(), type);
        //从用户关注列表中移除目标id
        redisTemplate.opsForZSet().remove(key, String.valueOf(targetId));
        //添加到消息队列
        Message message = Message.createMessage(CurrentUser.get().getId(), type, targetId).add("follow", false);
        rabbitTemplate.convertAndSend("follow.queue", message);
        //返回目标粉丝数量
        return getFollowerCount(type, targetId);
    }


}
