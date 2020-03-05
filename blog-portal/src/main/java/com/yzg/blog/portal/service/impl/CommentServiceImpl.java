package com.yzg.blog.portal.service.impl;

import com.github.pagehelper.PageHelper;
import com.yzg.blog.mapper.CmsCommentMapper;
import com.yzg.blog.model.CmsComment;
import com.yzg.blog.model.CmsCommentExample;
import com.yzg.blog.portal.dao.CmsCommentDao;
import com.yzg.blog.portal.controller.dto.CommentCreateDTO;
import com.yzg.blog.portal.controller.dto.CommentListDTO;
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

    @Override
    public int add(CommentCreateDTO params) {
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
            if (params.getTargetId() != 0) {
                CommentDao.addReplyCount(params.getTargetId(), 1);
            }
        }
        return result;
    }

    @Override
    public List<CmsComment> list(Integer pageNum, Integer pageSize, CommentListDTO params) {
        CmsCommentExample example = new CmsCommentExample();
        CmsCommentExample.Criteria criteria = example.createCriteria();
        if (pageSize > 20) {//最多查询20条数据
            pageSize = 20;
        }
        criteria.andParentIdEqualTo(params.getParentId())
                .andParentTypeEqualTo(params.getParentType())
                .andTargetIdEqualTo(params.getTargetId())
                .andStatusEqualTo((byte) 1);//查询状态为1（正常）的评论/回复
        example.setOrderByClause(params.getOrderBy());
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
                .andUserIdEqualTo(CurrentUser.get().getId());//当前登录用户id
        return commentMapper.updateByExampleSelective(comment, example);
    }

    @Override
    public int delete(Integer id) {
        CmsComment comment = new CmsComment();
        comment.setStatus((byte) 2);//设置status为 (2：已删除)
        CmsCommentExample example = new CmsCommentExample();
        example.createCriteria()
                .andIdEqualTo(id)
                .andUserIdEqualTo(CurrentUser.get().getId());//当前登录用户id
        return commentMapper.updateByExampleSelective(comment, example);
    }
}
