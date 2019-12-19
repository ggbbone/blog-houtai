package com.yzg.blog.portal.controller;

import com.github.pagehelper.PageHelper;
import com.yzg.blog.common.api.CommonPage;
import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.mapper.BmsArticleMapper;
import com.yzg.blog.model.BmsArticle;
import com.yzg.blog.model.BmsArticleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "demo")
public class DemoController {
    @Autowired
    BmsArticleMapper articleMapper;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult getArticleById(@PathVariable(value = "id") int id) {
        BmsArticle article = articleMapper.selectByPrimaryKey(id);
        System.out.println("中文");
        return CommonResult.success(article);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public CommonResult<CommonPage> demo() {
        PageHelper.offsetPage(1,5);
        List<BmsArticle> articles = articleMapper.selectByExample(new BmsArticleExample());
        System.out.println("hello");
        return CommonResult.success(CommonPage.restPage(articles));
    }
}
