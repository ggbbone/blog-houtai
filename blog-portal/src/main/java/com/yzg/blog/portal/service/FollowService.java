package com.yzg.blog.portal.service;

import com.yzg.blog.portal.controller.dto.FollowDTO;
import org.springframework.stereotype.Service;

/**
 * Created by yzg on 2020/1/2
 *
 * 用户模块关注功能service
 */

public interface FollowService {

    int follow(FollowDTO params);

    int unFollow(FollowDTO params);
}
