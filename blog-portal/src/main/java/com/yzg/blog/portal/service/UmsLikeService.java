package com.yzg.blog.portal.service;

import com.yzg.blog.portal.dto.UmsLikeCommonParams;

/**
 * Created by yzg on 2019/12/25
 */
public interface UmsLikeService {
    /**
     * 点赞
     * @param params
     * @return 点赞人数
     */
    Long like(UmsLikeCommonParams params) throws Exception;

    /**
     * 取消点赞
     * @param params
     * @return 点赞人数
     */
    Long unlike(UmsLikeCommonParams params) throws Exception;

    /**
     * @return 是否点赞
     */
    Boolean hasLike(int targetId, byte type);

    /**
     * @return 点赞总人数
     */
    Long getLikeCount(int targetId, byte type);


}
