package com.yzg.blog.portal.config;

import com.yzg.blog.portal.interceptor.DefaultInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author yangzg
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //权限验证拦截器
        registry.addInterceptor(new DefaultInterceptor())
                .addPathPatterns("/**");
    }
}
