package com.yzg.blog.portal.service.impl;

import com.github.pagehelper.PageHelper;
import com.yzg.blog.mapper.BmsArticleMapper;
import com.yzg.blog.model.BmsArticle;
import com.yzg.blog.model.BmsArticleExample;
import com.yzg.blog.portal.service.BmsArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yzg on 2019/12/19
 */
@Service
public class BmsArticleServiceImpl implements BmsArticleService {
    @Autowired
    BmsArticleMapper articleMapper;

    @Override
    public List<BmsArticle> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return articleMapper.selectByExample(new BmsArticleExample());
    }

    @Override
    public BmsArticle getById(int id) {
        return articleMapper.selectByPrimaryKey(id);
    }
}
