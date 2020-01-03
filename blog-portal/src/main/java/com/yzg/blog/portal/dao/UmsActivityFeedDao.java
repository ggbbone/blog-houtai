package com.yzg.blog.portal.dao;

import com.yzg.blog.model.UmsActivityFeed;
import org.apache.ibatis.annotations.Insert;

/**
 * Created by yzg on 2019/12/31
 *
 * 用户动态Dao
 */
public interface UmsActivityFeedDao {

    /**
     * 插入用户动态，如果动态已存在则更新动态
     * @param activityFeed 动态
     */
    @Insert("INSERT INTO " +
            "ums_activity_feed(actor_id,target_id,action,`status`,created_date) " +
            "VALUE(#{actorId},#{targetId},#{action},#{status},#{createdDate}) " +
            "ON DUPLICATE KEY UPDATE ums_activity_feed.`status` = #{status};")
    void insertOrUpdate(UmsActivityFeed activityFeed);
}
