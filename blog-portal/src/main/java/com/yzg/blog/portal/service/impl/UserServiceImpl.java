package com.yzg.blog.portal.service.impl;

import cn.hutool.Hutool;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;
import com.yzg.blog.common.exception.BadRequestException;
import com.yzg.blog.common.exception.BizException;
import com.yzg.blog.common.exception.UnauthorizedException;
import com.yzg.blog.common.utils.TokenUtils;
import com.yzg.blog.dao.mbg.mapper.UmsUserInfoMapper;
import com.yzg.blog.dao.mbg.mapper.UmsUserMapper;
import com.yzg.blog.dao.mbg.model.UmsUser;
import com.yzg.blog.dao.mbg.model.UmsUserExample;
import com.yzg.blog.dao.mbg.model.UmsUserInfo;
import com.yzg.blog.portal.controller.dto.UserDTO;
import com.yzg.blog.portal.service.UserService;
import com.yzg.blog.portal.utils.RedisKeysUtil;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

/**
 * @author yangzg
 */
@Service
@CacheConfig(cacheNames = "CACHE:USER")
public class UserServiceImpl implements UserService {
    @Resource
    UmsUserMapper userMapper;
    @Resource
    UmsUserInfoMapper userInfoMapper;
    @Autowired
    StringRedisTemplate redisTemplate;

    @Cacheable(value = "CACHE:USER", key = "#id")
    @Override
    public UmsUser getUserById(Integer id) {

        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    @Cacheable(value = "CACHE:USER_INFO", key = "#id")
    public UmsUserInfo getUserInfoById(Integer id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public UmsUserInfo getUserInfoByToken(String token) {
        Long userId = TokenUtils.getUserIdByTokenNoExp(token);
        if (userId != null) {
            return getUserInfoById(Math.toIntExact(userId));
        }
        return null;
    }

    @Override
    public Long getUserIps() {

        return redisTemplate.opsForSet().size(RedisKeysUtil.getUserIps());
    }

    @Override
    public Long getRequests() {
        return Long.valueOf(Objects.requireNonNull(redisTemplate.opsForValue().get(RedisKeysUtil.getRequests())));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String register(UserDTO dto) {
        String email = dto.getEmail();
        String password = dto.getPassword();
        String confirmPassword = dto.getConfirmPassword();
        if (!password.equals(confirmPassword)) {
            throw new BadRequestException("密码和确认密码不相同");
        }
        UmsUser userByEmail = getUserByEmail(email);
        if (userByEmail != null) {
            throw new BadRequestException("邮箱已经存在");
        }
        String salt = RandomUtil.randomString(6);
        String md5Password = SecureUtil.md5(password + salt);
        UmsUser user = new UmsUser();
        user.setEmail(email);
        user.setPassword(md5Password);
        user.setSalt(salt);
        userMapper.insertSelective(user);
        if (user.getId() != null) {
            Integer userId = user.getId();
            UmsUserInfo userInfo = new UmsUserInfo();
            userInfo.setUserId(userId);
            userInfo.setUsername("用户" + userId);
            //默认头像
            userInfo.setAvatar("https://images.nowcoder.com/head/" + RandomUtil.randomInt(1, 999) + "m.png");
            userInfoMapper.insertSelective(userInfo);
            //生成并返回token
            return TokenUtils.createToken(Long.valueOf(userId));
        }
        return "token生成失败";
    }

    @Override
    public UmsUser getUserByEmail(String email) {
        UmsUserExample example = new UmsUserExample();
        example.createCriteria().andEmailEqualTo(email);
        List<UmsUser> users = userMapper.selectByExample(example);
        return users.stream().findFirst().orElse(null);
    }

    @Override
    public String login(UserDTO dto) {
        String email = dto.getEmail();
        String password = dto.getPassword();
        UmsUser userByEmail = getUserByEmail(email);
        if (userByEmail != null) {
            if (SecureUtil.md5(password + userByEmail.getSalt()).equals(userByEmail.getPassword())) {
                return TokenUtils.createToken(Long.valueOf(userByEmail.getId()));
            }
        }
        throw new BadRequestException("用户名或密码错误");
    }

}
