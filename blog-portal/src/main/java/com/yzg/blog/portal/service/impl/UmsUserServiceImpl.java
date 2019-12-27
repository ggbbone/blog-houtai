package com.yzg.blog.portal.service.impl;

import com.yzg.blog.mapper.UmsUserInfoMapper;
import com.yzg.blog.mapper.UmsUserLoginLogMapper;
import com.yzg.blog.model.UmsUserInfo;
import com.yzg.blog.model.UmsUserInfoExample;
import com.yzg.blog.model.UmsUserLoginLog;
import com.yzg.blog.portal.dto.UmsLoginParams;
import com.yzg.blog.portal.dto.UmsRegisterParams;
import com.yzg.blog.portal.service.UmsUserService;
import com.yzg.blog.portal.utils.IpUtils;
import com.yzg.blog.portal.utils.RedisKeysUtils;
import com.yzg.blog.portal.utils.TokenUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by yzg on 2019/12/24
 */
@Service
public class UmsUserServiceImpl implements UmsUserService {
    @Autowired
    private UmsUserInfoMapper userInfoMapper;
    @Autowired
    private UmsUserLoginLogMapper userLoginLogMapper;
    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public String getLoginCode(String name) {
        return null;
    }

    @Override
    public String login(UmsLoginParams params, HttpServletRequest request) {
        UmsUserInfo user;
        //通过用户名登录
        user = getUserByUsername(params.getUsername());
        if (user == null){
            //用户名不存在时，使用邮箱登录
            user = getUserByEmail(params.getUsername());
        }
        if (user == null){
            throw new RuntimeException("用户名/邮箱不存在");
        }
        //md5加密密码
        String password = DigestUtils.md5DigestAsHex((params.getPassword() + user.getSalt()).getBytes());
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("密码不正确");
        }
        //返回token
        String token = TokenUtils.getToken(user);
        //写入登录记录
        UmsUserLoginLog loginLog = new UmsUserLoginLog();
        loginLog.setUserId(user.getId());
        loginLog.setLoginDate(new Date());
        loginLog.setToken(token);
        loginLog.setIp(IpUtils.getIpAddr(request));
        loginLog.setType((byte) 1);//设置登录方式（1：pc）
        userLoginLogMapper.insertSelective(loginLog);

        return token;
    }

    @Override
    public UmsUserInfo getUserById(int id) {
        UmsUserInfo userInfo;
        //从redis缓存中查询用户信息
        String key = RedisKeysUtils.getCacheUsers(id);
        userInfo = (UmsUserInfo) redisTemplate.opsForValue().get(key);

        if (userInfo == null) {
            //缓存不存在，从数据库中查询并将结果缓存
            userInfo = userInfoMapper.selectByPrimaryKey(id);
            if (userInfo != null) {
                redisTemplate.opsForValue().set(key, userInfo, 1800, TimeUnit.SECONDS);
            }
        }

        return userInfo;
    }

    @Override
    public String register(UmsRegisterParams params) {
        //注册验证
        if (getUserByUsername(params.getUsername()) != null) {
            throw new RuntimeException("用户名已存在");
        }
        if (getUserByEmail(params.getEmail()) != null) {
            throw new RuntimeException("邮箱已被注册");
        }
        UmsUserInfo userInfo = new UmsUserInfo();
        String salt = RandomStringUtils.random(5);//生成随机字符串盐
        userInfo.setSalt(salt);
        userInfo.setUsername(params.getUsername());
        //加密密码
        userInfo.setPassword(DigestUtils.md5DigestAsHex((params.getPassword() + salt).getBytes()));
        userInfo.setEmail(params.getEmail());
        userInfo.setStatus((byte) 1);
        userInfo.setCreatedDate(new Date());
        userInfoMapper.insertSelective(userInfo);
        return TokenUtils.getToken(userInfo);
    }

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    @Override
    public UmsUserInfo getUserByUsername(String username) {
        UmsUserInfoExample example = new UmsUserInfoExample();
        example.createCriteria().andUsernameEqualTo(username);
        if (userInfoMapper.selectByExample(example).size() == 0) {
            return null;
        } else {
            return userInfoMapper.selectByExample(example).get(0);
        }
    }

    /**
     * 根据邮箱查询用户
     *
     * @param email
     * @return
     */
    @Override
    public UmsUserInfo getUserByEmail(String email) {
        UmsUserInfoExample example = new UmsUserInfoExample();
        example.createCriteria().andEmailEqualTo(email);
        if (userInfoMapper.selectByExample(example).size() == 0) {
            return null;
        } else {
            return userInfoMapper.selectByExample(example).get(0);
        }
    }
}
