package com.yzg.blog.portal.interceptor;

import com.yzg.blog.common.exception.ForbiddenException;
import com.yzg.blog.common.exception.UnauthorizedException;
import com.yzg.blog.common.utils.RequestUtils;
import com.yzg.blog.common.exception.BizException;
import com.yzg.blog.common.utils.TokenUtils;
import com.yzg.blog.portal.security.Logical;
import com.yzg.blog.portal.security.RequiresRoles;
import com.yzg.blog.portal.security.SecurityManager;
import com.yzg.blog.portal.security.SecurityUtils;
import com.yzg.blog.portal.utils.RedisKeysUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author yangzg
 */
@Slf4j
public class DefaultInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    StringRedisTemplate redisTemplate;
    @Resource
    SecurityManager securityManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws BizException {
        String requestMethod = request.getMethod();
        if ("OPTIONS".equals(requestMethod))
            return true;
        String requestUri = request.getRequestURI();
        String token = request.getHeader("token");
        String ipAddress = RequestUtils.getIpAddress(request);
        log.info("Method:{}, URI:{}, IP:{}, token:{}", requestMethod, requestUri, ipAddress, token);
        //记录ip地址加入访问用户数
        redisTemplate.opsForSet().add(RedisKeysUtil.getUserIps(), ipAddress);
        //权限校验
        annotation(token, request, response, handler);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadUser.remove();
    }

    public void annotation(String token, HttpServletRequest request, HttpServletResponse response, Object handler) {
        Long userId = TokenUtils.getUserIdByTokenNoExp(token);
        if (userId != null) {
            ThreadUser.set(Math.toIntExact(userId));
        }
        HandlerMethod handlerMethod;
        try {
            handlerMethod = (HandlerMethod) handler;
        } catch (Exception e) {
            log.error("(HandlerMethod) handler", e);
            return;
        }
        Method method = handlerMethod.getMethod();
        RequiresRoles requiresRoles = method.getAnnotation(RequiresRoles.class);
        if (Objects.nonNull(requiresRoles)) {
            //需要登陆
            if (userId != null) {
                //权限验证
                String[] roles = requiresRoles.value();
                Logical logical = requiresRoles.logical();
                if (roles.length > 0 && !"".equals(roles[0])) {
                    boolean hasRoles = securityManager.hasRoles(String.valueOf(userId), logical, roles);
                    if (!hasRoles)
                        throw new ForbiddenException("权限不足,userId = " + userId);
                }
            } else {
                throw new UnauthorizedException("未登录");
            }
        }
    }

}
