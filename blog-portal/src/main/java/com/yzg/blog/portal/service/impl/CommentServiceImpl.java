package com.yzg.blog.portal.service.impl;


import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import com.yzg.blog.common.exception.BizException;
import com.yzg.blog.common.utils.BeanCopyUtils;
import com.yzg.blog.dao.mbg.mapper.BmsArticleMapper;
import com.yzg.blog.dao.mbg.mapper.CmsCommentMapper;
import com.yzg.blog.dao.mbg.model.*;
import com.yzg.blog.portal.controller.dto.ArticleDTO;
import com.yzg.blog.portal.controller.dto.CommentDTO;
import com.yzg.blog.portal.controller.vo.ArticleInfoVo;
import com.yzg.blog.portal.controller.vo.ArticleTagVo;
import com.yzg.blog.portal.controller.vo.CommentInfoVo;
import com.yzg.blog.portal.dao.ArticleDao;
import com.yzg.blog.portal.dao.CommentDao;
import com.yzg.blog.portal.service.*;
import com.yzg.blog.portal.utils.RedisKeysUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangzg
 */
@Service
@Slf4j
@CacheConfig(cacheNames = "CACHE:COMMENT")
public class CommentServiceImpl implements CommentService {

    @Resource
    CmsCommentMapper commentMapper;
    @Resource
    CommentDao commentDao;
    @Resource
    ArticleService articleService;

    @Resource
    UserService userService;

    @Override
    public List<CommentInfoVo> getCommentList(CommentDTO params) {
        CmsCommentExample example = new CmsCommentExample();
        example.setOrderByClause(CommentSort.getOderByCode(params.getSort()));
        CmsCommentExample.Criteria criteria = example.createCriteria();
        if (params.getTargetId() != null) {
            criteria.andTargetIdEqualTo(params.getTargetId());
        }
        if (params.getParentId() != null && params.getParentType() != null) {
            criteria.andParentIdEqualTo(params.getParentId())
                    .andParentTypeEqualTo(params.getParentType());
        }
        criteria.andStatusEqualTo(CommentStatus.NORMAL.getStatus());
        List<CmsComment> comments = commentMapper.selectByExample(example);
        List<CommentInfoVo> commentInfoVos = new ArrayList<>();
        for (CmsComment comment : comments) {
            CommentInfoVo commentInfoVo = new CommentInfoVo();
            BeanCopyUtils.copy(comment, commentInfoVo);
            if (commentInfoVo.getUserId() != null) {
                commentInfoVo.setUserInfo(userService.getUserInfoById(commentInfoVo.getUserId()));
            }
            commentInfoVos.add(commentInfoVo);
        }

        return commentInfoVos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addComment(CommentDTO dto) {
        CmsComment comment = new CmsComment();
        BeanCopyUtils.copy(dto, comment);
        comment.setId(null);
        comment.setStatus(CommentStatus.NORMAL.getStatus());
        commentMapper.insertSelective(comment);
        if (comment.getId() != null) {
            if (comment.getParentType().equals(CommentType.ARTICLE.getStatus())) {
                //文章评论 + 1
                articleService.incrementCommentCount(comment.getParentId());
            }
            if (comment.getTargetId() != null && comment.getTargetId() > 0) {
                //目标评论的回复 + 1
                commentDao.updateReplyCountById(comment.getTargetId(), 1);
            }
        }
        return comment.getId();
    }

    @Override
    public Integer updateCommentByIdAndUserId(CommentDTO dto) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteCommentByIdAndUserId(Integer id, Integer userId) {
        CmsCommentExample example = new CmsCommentExample();
        CmsComment comment = new CmsComment();
        comment.setStatus(CommentStatus.DELETE.getStatus());
        example.createCriteria()
                .andIdEqualTo(id)
                .andUserIdEqualTo(userId)
                .andStatusEqualTo(CommentStatus.NORMAL.getStatus());
        return commentMapper.updateByExampleSelective(comment, example);
    }

    public enum CommentStatus {
        //已删除
        DELETE((byte) 0),
        //正常
        NORMAL((byte) 1);

        private final byte status;

        CommentStatus(byte status) {
            this.status = status;
        }

        public byte getStatus() {
            return status;
        }
    }

    public enum CommentType {
        //文章评论
        ARTICLE((byte) 1),
        //话题评论
        PIN((byte) 2);

        private final byte status;

        CommentType(byte status) {
            this.status = status;
        }

        public byte getStatus() {
            return status;
        }
    }

    public enum CommentSort {
        //默认
        ID(1, "id desc"),
        VIEW_COUNT(2, "reply_count desc"),
        LIKE_COUNT(3, "likes_count desc");

        private final int code;
        private final String orderBy;

        public static String getOderByCode(Integer code) {
            if (code == null) {
                return ID.getOrderBy();
            }
            for (CommentServiceImpl.CommentSort g : values()) {
                if (g.getCode() == code) {
                    return g.getOrderBy();
                }
            }
            return ID.getOrderBy();
        }

        CommentSort(int code, String desc) {
            this.orderBy = desc;
            this.code = code;
        }

        public String getOrderBy() {
            return orderBy;
        }

        public int getCode() {
            return code;
        }
    }
}
