package com.yzg.blog.portal.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.yzg.blog.mapper", "com.yzg.blog.portal.mapper"})
public class MyBatisConfig {
}