package com.yzg.blog.portal.annotation;


import java.lang.annotation.*;

/**
 * @author yangzg
 *
 * 用于接口方法权限验证的注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableMethodSecurity {
    String value() default "";
}
