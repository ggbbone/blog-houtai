package com.yzg.blog.portal.service;

import com.yzg.blog.portal.controller.dto.CommentDTO;
import com.yzg.blog.portal.controller.vo.CommentInfoVo;

import java.util.List;

public interface CommentService {
    List<CommentInfoVo> getCommentList(CommentDTO params);

    Integer addComment(CommentDTO dto);

    Integer updateCommentByIdAndUserId(CommentDTO dto);

    Integer deleteCommentByIdAndUserId(Integer id, Integer userId);
}
