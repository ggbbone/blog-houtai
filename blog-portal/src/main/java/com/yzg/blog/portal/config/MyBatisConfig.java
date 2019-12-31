package com.yzg.blog.portal.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置
 */
@Configuration
@MapperScan({"com.yzg.blog.mapper", "com.yzg.blog.portal.dao"})
public class MyBatisConfig {
}
