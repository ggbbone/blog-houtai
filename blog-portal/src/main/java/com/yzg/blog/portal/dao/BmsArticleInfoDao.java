package com.yzg.blog.portal.dao;

import com.yzg.blog.portal.controller.dto.ArticleListDTO;
import com.yzg.blog.portal.model.ArticleInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by yzg on 2020/1/7
 *
 * 自定义文章详细信息dao
 */
public interface BmsArticleInfoDao {

    String selectAll = "SELECT id, user_id AS userId, outline, category_id AS categoryId, " +
            "title, cover, created_date as createdDate, " +
            "updated_date AS updateDate, last_comment_time AS lastCommentTime, \n" +
            "like_count AS likeCount, view_count AS viewCount, comment_count AS commentCount, " +
            "recommend_index AS recommendIndex, hot, hot_index AS hotIndex, status\n";

    @Select("<script>" +
            "SELECT id, user_id AS userId, outline, category_id AS categoryId, " +
            "title, cover, created_date as createdDate, " +
            "updated_date AS updateDate, last_comment_time AS lastCommentTime, \n" +
            "like_count AS likeCount, view_count AS viewCount, comment_count AS commentCount, " +
            "recommend_index AS recommendIndex, hot, hot_index AS hotIndex, status\n" +
            "from bms_article" +
            "<where> " +
            "<if test='params.userId != null'> " +
            "AND user_id = #{params.userId}" +
            "</if> " +
            "<if test='params.categoryId != null'> " +
            "AND category_id = #{params.categoryId}" +
            "</if> " +
            "<if test='params.orderBy == 3'> " +
            "AND hot = 1" +
            "</if> " +
            "AND status = 1 " +
            "</where> " +
            "<choose>" +
            "<when test='params.orderBy == 2'> " +
            "ORDER BY view_count DESC " +
            "</when> " +
            "<when test='params.orderBy == 3'> " +
            "ORDER BY hot_index DESC " +
            "</when> " +
            "<otherwise>" +
            "ORDER BY id DESC " +
            "</otherwise>" +
            "</choose>" +
            "</script> ")
    List<ArticleInfo> list(@Param("params") ArticleListDTO params);


    @Select("SELECT id, user_id AS userId, content, category_id AS categoryId, " +
            "title, cover, created_date as createdDate, " +
            "updated_date AS updateDate, last_comment_time AS lastCommentTime, \n" +
            "like_count AS likeCount, view_count AS viewCount, comment_count AS commentCount, " +
            "recommend_index AS recommendIndex, hot, hot_index AS hotIndex, status\n" +
            "from bms_article " +
            "where id = #{id} " +
            "AND status = 1 ")
    @Results(id = "userAndCategory", value = {
            @Result(property = "user", column = "userId",
                    one = @One(select = "com.yzg.blog.mapper.UmsUserInfoMapper.selectByPrimaryKey")),
            @Result(property = "category", column = "categoryId",
                    one = @One(select = "com.yzg.blog.portal.dao.BmsCategoryDao.getById"))
    })
    ArticleInfo getById(Integer id);


    /**
     * 添加文章浏览次数
     * @param articleId 文章id
     * @param count 增加的浏览次数
     */
    @Update("UPDATE bms_article SET view_count = view_count + #{count} WHERE id = #{articleId}")
    int addViewCount(@Param("articleId") int articleId, @Param("count") int count);

    /**
     * 增加文章回复数量
     * @param articleId
     * @param count
     * @return
     */
    @Update("UPDATE bms_article SET comment_count = comment_count + #{count} WHERE id = #{articleId}")
    int addCommentCount(Integer articleId, int count);
}
