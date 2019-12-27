package com.yzg.blog.portal.service.impl;

import com.github.pagehelper.PageHelper;
import com.yzg.blog.mapper.CmsCommentMapper;
import com.yzg.blog.model.CmsComment;
import com.yzg.blog.model.CmsCommentExample;
import com.yzg.blog.portal.dto.CmsCommentCreateParams;
import com.yzg.blog.portal.dto.CmsCommentListParams;
import com.yzg.blog.portal.service.CmsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by yzg on 2019/12/20
 */
@Service
public class CmsCommentServiceImpl implements CmsCommentService {
    @Autowired
    CmsCommentMapper commentMapper;

    @Override
    public int add(CmsCommentCreateParams params) {
        CmsComment comment = new CmsComment();
        comment.setContent(params.getContent());
        comment.setTargetId(params.getTargetId());
        comment.setRespUserId(params.getRespUserId());
        comment.setParentType(params.getType());
        comment.setParentId(params.getParentId());
        comment.setCreatedDate(new Date());
        comment.setUpdatedDate(new Date());
        comment.setUserId(1);//当前登录用户id
        return commentMapper.insertSelective(comment);
    }

    @Override
    public List<CmsComment> list(Integer pageNum, Integer pageSize, CmsCommentListParams params) {
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
                .andUserIdEqualTo(1);//当前登录用户id
        return commentMapper.updateByExampleSelective(comment, example);
    }

    @Override
    public int delete(Integer id) {
        CmsComment comment = new CmsComment();
        comment.setStatus((byte) 2);//设置status为 (2：已删除)
        CmsCommentExample example = new CmsCommentExample();
        example.createCriteria()
                .andIdEqualTo(id)
                .andUserIdEqualTo(1);//当前登录用户id
        return commentMapper.updateByExampleSelective(comment, example);
    }
}
