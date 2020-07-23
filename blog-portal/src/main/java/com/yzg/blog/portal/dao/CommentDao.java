package com.yzg.blog.portal.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * @author yangzg
 */
@Mapper
public interface CommentDao {

    int updateReplyCountById(@Param("id") Integer id, @Param("count") int commentCount);

}
