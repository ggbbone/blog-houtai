package com.yzg.blog.portal.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


/**
 * @author yangzg
 *
 * mybatis配置
 */
@Configuration
@MapperScan({"com.yzg.blog.dao.mbg.mapper", "com.yzg.blog.portal.dao"})
public class MyBatisConfig {

}
