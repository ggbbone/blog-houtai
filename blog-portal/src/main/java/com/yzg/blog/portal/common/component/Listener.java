package com.yzg.blog.portal.common.component;

import com.yzg.blog.mapper.UmsUserLoginLogMapper;
import com.yzg.blog.model.BmsArticle;
import com.yzg.blog.model.UmsUserLoginLog;
import com.yzg.blog.portal.controller.dto.LikeCommonDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by yzg on 2019/12/30
 *
 * rabbitMQ消费者
 */
@Component
@Slf4j
public class Listener {
    @Autowired
    private UmsUserLoginLogMapper userLoginLogMapper;


    /**
     * 用户登录消息队列处理
     * @param loginLog 登录日志信息
     */
    @RabbitListener(queuesToDeclare = @Queue("login.queue"))
    public void loginMq(UmsUserLoginLog loginLog) throws Exception {
        log.info("用户登录：" + loginLog);
        userLoginLogMapper.insertSelective(loginLog);
    }

    /**
     * 用户 点赞/取消点赞 消息队列处理
     * @param
     */
    @RabbitListener(queuesToDeclare = @Queue("like.queue"))
    public void likeMq(LikeCommonDTO params) throws Exception {
        log.info("用户点赞：" + params);

    }

    /**
     * 用户发表文章消息队列处理
     * @param article 文章
     */
    @RabbitListener(queuesToDeclare = @Queue("add.article.queue"))
    public void addArticleMq(BmsArticle article) {
        log.info("用户发表文章：" + article);

    }
}
