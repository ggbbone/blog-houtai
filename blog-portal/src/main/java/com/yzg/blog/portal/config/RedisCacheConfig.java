package com.yzg.blog.portal.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;


/**
 * @author yangzg
 *
 * 配置spring chache 基于redis
 */
@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {

}
