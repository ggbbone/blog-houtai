package com.yzg.blog.portal.security;

import com.yzg.blog.common.exception.UnauthorizedException;
import com.yzg.blog.common.utils.TokenUtils;
import com.yzg.blog.portal.interceptor.ThreadUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Objects;

@Slf4j
public class SecurityUtils {
    /**
     * 权限验证
     * @param token token
     * @param handler 接口handler
     */



}
