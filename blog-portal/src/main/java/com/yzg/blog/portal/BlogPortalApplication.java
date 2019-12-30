package com.yzg.blog.portal;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.yzg")
@EnableCaching
@EnableRabbit
public class BlogPortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogPortalApplication.class, args);
    }
}
