package com.yzg.blog.portal.security;

import com.yzg.blog.dao.mbg.model.SysRole;
import com.yzg.blog.portal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class SecurityManager {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Resource
    RoleService roleService;

    private static final String key = "CACHE_SESSION_ROLES:";

    public void addRoleCache(String sessionId, String... roles){
        if (roles.length == 0)
            roles = new String[]{""};
        redisTemplate.opsForSet().add(key + sessionId, roles);
        redisTemplate.expire(key + sessionId, 5, TimeUnit.MINUTES);
    }

    public void clearCache(String sessionId) {
        redisTemplate.delete(key + sessionId);
    }

    public Set<String> getRoles(String sessionId) {
        Boolean hasKey = redisTemplate.hasKey(key + sessionId);
        if (hasKey != null && !hasKey) {
            //添加到用户角色到缓存
            List<SysRole> sysRoles = roleService.getRolesByUserId(Integer.valueOf(sessionId));
            List<String> roleNames = sysRoles.stream().map(SysRole::getName).collect(Collectors.toList());
            String [] arr = new String[roleNames.size()];
            addRoleCache(sessionId, roleNames.toArray(arr));
        }
        return redisTemplate.opsForSet().members(key + sessionId);
    }

    public boolean hasRoles(String sessionId,Logical logical, String... roles){
        Set<String> members = getRoles(sessionId);
        if (members != null) {
            members.retainAll(Arrays.asList(roles));
            if (logical == Logical.AND) {
                return members.size() == roles.length;
            } else if (logical == Logical.OR) {
                return members.size() > 0;
            }
        }

        return false;
    }

    public boolean hasRole(String sessionId, String role){
        Boolean member = redisTemplate.opsForSet().isMember(key + sessionId, role);
        return member != null ? member : false;
    }

}
