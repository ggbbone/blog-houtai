package com.yzg.blog.portal.config;

import com.yzg.blog.portal.interceptor.DefaultInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author yangzg
 *
 * web配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //权限验证拦截器
        registry.addInterceptor(new DefaultInterceptor())
                .excludePathPatterns("/swagger-ui.html")
                .excludePathPatterns("/configuration/ui")
                .excludePathPatterns("/swagger-resources")
                .excludePathPatterns("/configuration/security")
                .excludePathPatterns("/v2/api-docs")
                .excludePathPatterns("/error")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/**/favicon.ico")
                .addPathPatterns("/**");
    }
}
