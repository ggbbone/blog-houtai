package com.yzg.blog.portal;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("com.yzg.blog")//扫描包下的注解
//@EnableCaching//开启缓存自动配置
@EnableRabbit//开启rabbit消息队列
@EnableScheduling//开启定时任务
public class BlogPortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogPortalApplication.class, args);
    }
}
