package com.yzg.blog.portal.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * Created by yzg on 2020/1/13
 *
 * 自定义评论dao
 */
public interface CmsCommentDao {

    /**
     * 添加评论浏览次数
     * @param commentId 文章id
     * @param count 增加的浏览次数
     */
    @Update("UPDATE cms_comment SET reply_count = reply_count + #{count} WHERE ID = #{commentId}")
    void addReplyCount(@Param("commentId") int commentId, @Param("count") int count);


}
