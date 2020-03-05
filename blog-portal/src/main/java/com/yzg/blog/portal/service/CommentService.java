package com.yzg.blog.portal.service;

import com.yzg.blog.model.CmsComment;
import com.yzg.blog.portal.controller.dto.CommentCreateDTO;
import com.yzg.blog.portal.controller.dto.CommentListDTO;

import java.util.List;

/**
 * Created by yzg on 2019/12/20
 *
 */
public interface CommentService {

    /**
     * 添加评论
     * @param params
     */
    int add(CommentCreateDTO params);

    /**
     * 分页查询评论
     * @param pageNum 页码
     * @param pageSize 页大小
     * @param comment 其他条件
     * @return 查询结果集
     */
    List<CmsComment> list(Integer pageNum, Integer pageSize, CommentListDTO comment);

    /**
     * 更新评论内容
     * @param id 评论id
     * @param content 内容
     * @return 更新成功的数量
     */
    int update(Integer id, String content);

    /**
     * 删除评论
     * @param id 评论id
     * @return 删除成功的数量
     */
    int delete(Integer id);
}
