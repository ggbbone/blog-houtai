package com.yzg.blog.portal.security;



import java.lang.annotation.*;

/**
 * @author yangzg
 *
 * 用于接口方法权限验证的注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiresRoles {
    String[] value() default "";

    Logical logical() default Logical.AND;
}
