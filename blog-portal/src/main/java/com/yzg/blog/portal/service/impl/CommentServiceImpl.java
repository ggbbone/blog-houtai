package com.yzg.blog.portal.service.impl;

import com.github.pagehelper.PageHelper;
import com.yzg.blog.mapper.CmsCommentMapper;
import com.yzg.blog.model.CmsComment;
import com.yzg.blog.model.CmsCommentExample;
import com.yzg.blog.portal.common.exception.ValidateFailedException;
import com.yzg.blog.portal.dao.CmsCommentDao;
import com.yzg.blog.portal.controller.dto.CommentCreateDTO;
import com.yzg.blog.portal.controller.dto.CommentListDTO;
import com.yzg.blog.portal.model.ECommentParentType;
import com.yzg.blog.portal.model.ECommentStatus;
import com.yzg.blog.portal.model.EOrderBy;
import com.yzg.blog.portal.service.ArticleService;
import com.yzg.blog.portal.service.CommentService;
import com.yzg.blog.portal.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by yzg on 2019/12/20
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CmsCommentMapper commentMapper;
    @Autowired
    CmsCommentDao CommentDao;
    @Autowired
    ArticleService articleService;

    @Override
    public int add(CommentCreateDTO params) throws ValidateFailedException {
        if (ECommentParentType.getDesc(params.getParentType()) == null) {
            throw new ValidateFailedException("评论提交失败: parentType不存在");
        }

        CmsComment comment = new CmsComment();
        comment.setContent(params.getContent());
        comment.setTargetId(params.getTargetId());
        comment.setRespUserId(params.getRespUserId());
        comment.setParentType(params.getParentType());
        comment.setParentId(params.getParentId());
        comment.setCreatedDate(new Date());
        comment.setUpdatedDate(new Date());
        comment.setUserId(CurrentUser.get().getId());
        //插入评论
        int result = commentMapper.insertSelective(comment);
        //评论添加成功后
        if (result > 0) {
            //如果是回复，目标评论的回复数量 + 1
            int notParentComment = 0;
            if (params.getTargetId() != notParentComment) {
                CommentDao.addReplyCount(params.getTargetId(), 1);
            }
            if (ECommentParentType.ARTICLE.getCode() == params.getParentType()) {
                //文章回复 + 1
                articleService.addArticleCommentCount(params.getParentId(), 1);
            } else if (ECommentParentType.PIN.getCode() == params.getParentType()) {
                //讨论回复 + 1
            }
        }
        return result;
    }

    @Override
    public List<CmsComment> list(Integer pageNum, Integer pageSize, CommentListDTO params) {
        CmsCommentExample example = new CmsCommentExample();
        CmsCommentExample.Criteria criteria = example.createCriteria();
        if (pageSize > 50) {//最多查询50条数据
            pageSize = 50;
        }
        criteria.andParentIdEqualTo(params.getParentId())
                .andParentTypeEqualTo(params.getParentType())
                .andTargetIdEqualTo(params.getTargetId())
                .andStatusEqualTo(ECommentStatus.NORMAL.getCode());//查询状态为1（正常）的评论/回复
        example.setOrderByClause(EOrderBy.getDesc(params.getOrderBy()) + " desc");
        PageHelper.startPage(pageNum, pageSize);
        return commentMapper.selectByExample(example);
    }

    @Override
    public int update(Integer id, String content) {
        CmsComment comment = new CmsComment();
        comment.setContent(content);
        CmsCommentExample example = new CmsCommentExample();
        example.createCriteria()
                .andIdEqualTo(id)
                .andStatusEqualTo(ECommentStatus.NORMAL.getCode())
                .andUserIdEqualTo(CurrentUser.get().getId());//当前登录用户id
        return commentMapper.updateByExampleSelective(comment, example);
    }

    @Override
    public int delete(Integer id) {
        CmsComment comment = new CmsComment();
        comment.setStatus(ECommentStatus.DELETE.getCode());
        CmsCommentExample example = new CmsCommentExample();
        example.createCriteria()
                .andIdEqualTo(id)
                .andUserIdEqualTo(CurrentUser.get().getId());//当前登录用户id
        return commentMapper.updateByExampleSelective(comment, example);
    }
}
