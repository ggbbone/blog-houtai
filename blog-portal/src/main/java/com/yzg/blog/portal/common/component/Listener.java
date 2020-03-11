package com.yzg.blog.portal.common.component;

import com.yzg.blog.mapper.UmsUserLoginLogMapper;
import com.yzg.blog.model.BmsArticle;
import com.yzg.blog.model.UmsUserLoginLog;
import com.yzg.blog.portal.controller.dto.LikeDTO;
import com.yzg.blog.portal.controller.dto.UserRegisterDTO;
import com.yzg.blog.portal.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

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
    @Autowired
    private EmailService emailService;


    /**
     * 用户注册验证消息队列处理
     * @param params 注册参数
     * @throws Exception
     */
    @RabbitListener(queuesToDeclare = @Queue("register.queue"))
    public void registerMq(UserRegisterDTO params) throws Exception {
        log.info("处理消息-用户注册：" + params);
        String code = UUID.randomUUID().toString().replaceAll("-","");
        //code存入redis
        String subject = "xxx-注册验证";
        String path = "http://127.0.0.1:8081/";
        String text = "点击链接完成注册<a>" + path + "check/register/"+ params.getEmail() + "?code=" + code+"</a>";
        emailService.sendSimpleEmail(params.getEmail(),subject, text);
    }

    /**
     * 用户登录消息队列处理
     * @param loginLog 登录日志信息
     */
    @RabbitListener(queuesToDeclare = @Queue("login.queue"))
    public void loginMq(UmsUserLoginLog loginLog) throws Exception {
        log.info("处理消息-用户登录：" + loginLog);
        userLoginLogMapper.insertSelective(loginLog);
    }

    /**
     * 用户 点赞/取消点赞 消息队列处理
     * @param
     */
    @RabbitListener(queuesToDeclare = @Queue("like.queue"))
    public void likeMq(LikeDTO params) throws Exception {
        log.info("处理消息-用户点赞：" + params);

    }

    /**
     * 用户发表文章消息队列处理
     * @param article 文章
     */
    @RabbitListener(queuesToDeclare = @Queue("add.article.queue"))
    public void addArticleMq(BmsArticle article) {
        log.info("处理消息-发表文章：" + article);

    }

    /**
     * 关注/取消关注 消息队列处理
     */
    @RabbitListener(queuesToDeclare = @Queue("follow.queue"))
    public void follow(Message message) {
        Boolean follow = (Boolean) message.getData().get("follow");
        if (follow) {
            log.info("关注：" + message);

        } else {
            log.info("取消关注：" + message);

        }
    }
}
