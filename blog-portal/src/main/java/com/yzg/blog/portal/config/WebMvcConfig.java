package com.yzg.blog.portal.config;

import com.yzg.blog.portal.interceptor.DefaultInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author yangzg
 *
 * web配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public DefaultInterceptor defaultInterceptor() {
        return new DefaultInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //权限验证拦截器
        registry.addInterceptor(defaultInterceptor())
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

    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").
                        allowedOrigins("*"). //允许跨域的域名，可以用*表示允许任何域名使用
                        allowedMethods("*"). //允许任何方法（post、get等）
                        allowedHeaders("*"). //允许任何请求头
                        allowCredentials(true). //带上cookie信息
                        exposedHeaders(HttpHeaders.SET_COOKIE).maxAge(300L); //maxAge(300)表明在300秒内，不需要再发送预检验请求，可以缓存该结果
            }
        };
    }
}
