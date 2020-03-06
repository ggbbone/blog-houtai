package com.yzg.blog.portal.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.MessageConverter;

/**
 * Created by yzg on 2019/12/30
 *
 * 消息队列配置
 */
//@Configuration
public class AmqpConfig {
    //使用Jackson序列化
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
