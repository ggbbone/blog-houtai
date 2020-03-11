package com.yzg.blog.portal.service;


import java.util.List;
import java.util.Set;

/**
 * Created by yzg on 2020/1/2
 *
 * 用户模块关注功能service
 */

public interface FollowService {

    Long follow(Byte type, Integer targetId);

    Long unFollow(Byte type, Integer targetId);

    Long getFollowerCount(Byte type, Integer targetId);

    Long getFolloweeCount(Integer userId, Integer type);

    Set<String> getFolloweeIdsPage(Integer userId, Byte type, int page, int size);
    Set<String> getFollowerIdsPage(Integer userId, Byte type, int page, int size);

    Boolean hasFollow(int targetId, byte type);

    List<Object> listFollowees(Integer userId, Byte type, int pageNum, int pageSize);

    List<Object> listFollowers(Integer userId, Byte type, int pageNum, int pageSize);
}
