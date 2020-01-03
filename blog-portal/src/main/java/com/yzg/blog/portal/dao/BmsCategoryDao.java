package com.yzg.blog.portal.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by yzg on 2020/1/2
 *
 * 标签管理Dao
 */
public interface BmsCategoryDao {

    /**
     * 批量插入文章和标签数据
     * @param tags 标签id集合
     * @param articleId 文章id
     */
    @Insert({
            "<script>",
            "insert into bms_article_tags(article_id, category_id) values ",
            "<foreach collection='tags' item='tag' index='index' separator=','>",
            "(#{articleId}, #{tag})",
            "</foreach>",
            "</script>"
    })
    void insertTags(@Param(value = "tags") List<Integer> tags, Integer articleId) throws DataAccessException;

    /**
     * 删除文章对应的标签
     * @param articleId 文章id
     */
    @Delete("delete bms_article_tags where article_id = #{articleId}")
    void deleteTags(Integer articleId);
}
