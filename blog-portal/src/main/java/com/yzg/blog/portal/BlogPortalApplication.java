package com.yzg.blog.portal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author yangzg
 */
@SpringBootApplication
@ComponentScan("com.yzg.blog")//扫描包下的注解
@MapperScan("com.yzg.blog.mapper")
@EnableScheduling//开启定时任务
@EnableCaching//开启缓存
public class BlogPortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogPortalApplication.class, args);
    }
}
