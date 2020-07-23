package com.yzg.blog.portal.utils;

/**
 * @author yangzg
 */
public class RedisKeysUtil {
    //分隔符
    public static final String SPLIT = ":";
    //访问用户ip
    private static final String BIZ_USER_IPS = "USER_IPS";
    //访问次数
    private static final String BIZ_REQUESTS = "REQUESTS";
    //点赞他的用户
    private static final String BIZ_LIKE = "LIKE";
    //文章浏览次数待同步队列
    private static final String BIZ_SYNC_ARTICLE_VIEW_COUNT = "SYNC_ARTICLE_VIEW_COUNT";
    //文章浏览次数
    private static final String BIZ_ARTICLE_VIEW_COUNT = "ARTICLE_VIEW_COUNT";
    //关注他的人
    private static final String BIZ_FOLLOWER = "FOLLOWER";
    //他关注的的人
    private static final String BIZ_FOLLOWEE = "FOLLOWEE";
    //token黑名单
    private static final String BIZ_TOKEN_FILTER = "TOKEN_FILTER";
    //提交反馈请求记录
    private static final String BIZ_POST_FEEDBACK_REQUEST_NUMBER = "POST_FEEDBACK_REQUEST_NUMBER";

    public static String getUserIps(){
        return BIZ_USER_IPS;
    }

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
     * 待同步文章同步队列
     * @return
     */
    public static String getSyncArticleViewCount() {
        return BIZ_SYNC_ARTICLE_VIEW_COUNT;
    }

    /**
     * 文章浏览次数
     * @param articleId 文章id
     * @return
     */
    public static String getArticleViewCountKey(int articleId) {
        return BIZ_ARTICLE_VIEW_COUNT + SPLIT + articleId;
    }

    /**
     * 获取token黑名单
     * @param token token
     * @return
     */
    public static String getTokenFilter(String token) {
        return BIZ_TOKEN_FILTER + SPLIT + token;
    }

    /**
     * 获取ip地址的请求次数
     * @param ipAddress ip地址
     * @return
     */
    public static String getPostFeedbackRequest(String ipAddress) {
        return BIZ_POST_FEEDBACK_REQUEST_NUMBER + SPLIT + ipAddress;
    }

    /**
     * 总访问次数
     * @return
     */
    public static String getRequests() {
        return BIZ_REQUESTS;
    }

}
