package com.yzg.blog.portal.interceptor;

import com.yzg.blog.portal.exception.BizException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yangzg
 */
public class DefaultInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws BizException {
        //权限验证处理
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        String token = request.getHeader("token");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
