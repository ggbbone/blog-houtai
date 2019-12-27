package com.yzg.blog.portal.Interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.yzg.blog.model.UmsUserInfo;
import com.yzg.blog.portal.annotation.LoginToken;
import com.yzg.blog.portal.exception.UnauthorizedException;
import com.yzg.blog.portal.service.UmsUserService;
import com.yzg.blog.portal.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by yzg on 2019/12/27
 *
 *  用于验证用户token的拦截器
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private UmsUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object object) throws Exception {
        // 从 http 请求头中取出 token
        String token = request.getHeader("token");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        Method method = ((HandlerMethod) object).getMethod();
        //检查需要用户权限的注解
        if (method.isAnnotationPresent(LoginToken.class)) {
            LoginToken loginToken = method.getAnnotation(LoginToken.class);
            if (loginToken.value()) {
                // 执行认证
                if (token == null) {
                    throw new UnauthorizedException("token is null");
                }
                // 获取 token 中的 user id
                String userId;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new UnauthorizedException("user is not found:"+j.getMessage());
                }
                UmsUserInfo user = userService.getUserById(Integer.parseInt(userId));
                if (user == null) {
                    throw new UnauthorizedException("user is null");
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new UnauthorizedException("token is field:"+e.getMessage());
                }
                //当前用户存入ThreadLocal
                CurrentUser.set(user);
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
        //调用结束时，移除ThreadLocal
        CurrentUser.remove();
    }
}