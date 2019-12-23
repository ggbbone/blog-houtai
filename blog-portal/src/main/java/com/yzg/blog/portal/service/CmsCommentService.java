package com.yzg.blog.portal.service;

import com.yzg.blog.model.CmsComment;
import com.yzg.blog.portal.dto.CmsCommentAddParams;
import com.yzg.blog.portal.dto.CmsCommentListParams;

import java.util.List;

/**
 * Created by yzg on 2019/12/20
 *
 */
public interface CmsCommentService {

    /**
     * 添加评论
     * @param params
     */
    int add(CmsCommentAddParams params);

    /**
     * 分页查询评论
     * @param pageNum 页码
     * @param pageSize 页大小
     * @param comment 其他条件
     * @return
     */
    List<CmsComment> list(Integer pageNum, Integer pageSize, CmsCommentListParams comment);

    /**
     * 更新评论内容
     * @param id
     * @param content
     * @return
     */
    int update(Integer id, String content);

    /**
     * 删除评论
     * @param id
     * @return
     */
    int delete(Integer id);
}
