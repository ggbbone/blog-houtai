package com.yzg.blog.portal.interceptor;

import com.yzg.blog.common.exception.UnauthorizedException;
import com.yzg.blog.common.utils.RequestUtils;
import com.yzg.blog.common.exception.BizException;
import com.yzg.blog.common.utils.TokenUtils;
import com.yzg.blog.portal.annotation.EnableMethodSecurity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author yangzg
 */
@Slf4j
public class DefaultInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws BizException {

        String requestMethod = request.getMethod();
        String requestUri = request.getRequestURI();
        String token = request.getHeader("token");
        String ipAddress = RequestUtils.getIpAddress(request);
        log.info("Method:{}, URI:{}, IP:{}, token:{}", requestMethod, requestUri, ipAddress, token);

        annotation(token, request, response, handler);


        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadUser.remove();
    }

    /**
     * 权限验证
     * @param token token
     * @param handler 接口handler
     */
    private void annotation(String token, HttpServletRequest request, HttpServletResponse response, Object handler) {
        Long userId = TokenUtils.getUserIdByTokenNoExp(token);
        if (userId != null) {
            ThreadUser.set(Math.toIntExact(userId));
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        EnableMethodSecurity enableMethodSecurity = method.getAnnotation(EnableMethodSecurity.class);
        if (Objects.nonNull(enableMethodSecurity)) {
            //需要登陆
            if (userId != null) {
                //权限验证

            } else {
                throw new UnauthorizedException("未登录");
            }
        }
    }
}
