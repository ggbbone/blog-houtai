package com.yzg.blog.portal.component;

import com.yzg.blog.mapper.UmsUserLoginLogMapper;
import com.yzg.blog.model.UmsUserLoginLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public void receiveLoginMq(UmsUserLoginLog loginLog) throws Exception {
        log.info("用户登录：" + loginLog);
        userLoginLogMapper.insertSelective(loginLog);
    }

}
