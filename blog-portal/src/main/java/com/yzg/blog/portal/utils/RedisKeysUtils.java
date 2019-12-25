package com.yzg.blog.portal.utils;

/**
 * Created by yzg on 2019/12/25
 *
 * 获取redis的key的工具类
 */
public class RedisKeysUtils {
    //分隔符
    private static String SPLIT = ":";

    //点赞用户
    private static String BIZ_LIKE = "LIKE";

    //点赞修改队列
    private static String BIZ_CHANG_LIKE = "CHANG_LIKE";

    //关注他的人
    private static String BIZ_FOLLOWER = "FOLLOWER";

    //他关注的的人
    private static String BIZ_FOLLOWEE = "FOLLOWEE";

    //登陆token
    private static String BIZ_LOGIN_TOKEN = "LOGIN_TOKEN";

    /**
     * 点赞用户列表
     * @param entityType 点赞目标类型
     * @param entityId 点赞目标id
     * @return
     */
    public static String getLikeKey(int entityType, int entityId){
        return BIZ_LIKE + SPLIT + entityType +
                SPLIT + entityId;
    }

    /**
     * 粉丝用户列表
     * @param entityType 被关注类型
     * @param entityId 被关注id
     * @return
     */
    public static String getFollowerKey(int entityType, int entityId){
        return BIZ_FOLLOWER + SPLIT + entityType +
                SPLIT + entityId;
    }

    /**
     * 关注列表
     * @param entityType 关注类型
     * @param userId 用户id
     * @return
     */
    public static String getFolloweeKey(int userId, int entityType){
        return BIZ_FOLLOWEE + SPLIT +
                userId + SPLIT + entityType;
    }

    /**
     * 点赞修改队列
     * @return
     */
    public static String getChangeLikeKey() {
        return BIZ_CHANG_LIKE;
    }

}
