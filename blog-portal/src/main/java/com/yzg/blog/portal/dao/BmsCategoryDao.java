package com.yzg.blog.portal.dao;

import com.yzg.blog.portal.model.BmsArticleTag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by yzg on 2020/1/2
 *
 * 自定义标签管理Dao
 */
public interface BmsCategoryDao {

    @Select("select title, id from bms_category where id = #{id}")
    BmsArticleTag getById(int id);

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
     * 删除文章的所有标签
     * @param articleId 文章id
     */
    @Delete("delete bms_article_tags where article_id = #{articleId}")
    void deleteTags(Integer articleId);

    /**
     * 查询文章的标签
     * @param id 文章id
     * @return 标签集合
     */
    @Select("SELECT t.category_id as id, c.title as title FROM\n" +
            "bms_article_tags t INNER JOIN bms_category c\n" +
            "on t.article_id = #{id} \n" +
            "and c.id = t.category_id \n" +
            "and c.`status` = 1")
    List<BmsArticleTag> selectArticleTagsByArticleId(@Param(value = "id")Integer id);
}
