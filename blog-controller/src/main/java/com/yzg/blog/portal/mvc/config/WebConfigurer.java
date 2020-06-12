package com.yzg.blog.portal.mvc.config;

import com.yzg.blog.portal.mvc.interceptor.SecurityInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author yangzg
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //权限验证拦截器
        registry.addInterceptor(new SecurityInterceptor())
                .addPathPatterns("/**");
    }
}
