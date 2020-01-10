package com.yzg.blog.portal.service.impl;

import com.yzg.blog.mapper.UmsUserInfoMapper;
import com.yzg.blog.mapper.UmsUserMapper;
import com.yzg.blog.model.UmsUser;
import com.yzg.blog.model.UmsUserExample;
import com.yzg.blog.model.UmsUserInfo;
import com.yzg.blog.model.UmsUserLoginLog;
import com.yzg.blog.portal.dto.UmsLoginParams;
import com.yzg.blog.portal.dto.UmsRegisterParams;
import com.yzg.blog.portal.exception.ValidateFailedException;
import com.yzg.blog.portal.service.UmsUserInfoService;
import com.yzg.blog.portal.service.UmsUserService;
import com.yzg.blog.portal.utils.IpUtils;
import com.yzg.blog.portal.utils.TokenUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by yzg on 2019/12/24
 *
 * 用户基础信息service
 */
@Service
@CacheConfig(cacheNames = {"C_USER"})
public class UmsUserServiceImpl implements UmsUserService {
    @Autowired
    private UmsUserMapper userMapper;
    @Autowired
    private UmsUserInfoMapper userInfoMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private UmsUserInfoService userInfoService;

    @Override
    public String login(UmsLoginParams params, HttpServletRequest request) throws Exception {
        //校验用户
        UmsUser user;
        user = getUserByEmail(params.getUsername());
        if (user == null){
            throw new ValidateFailedException("用户不存在");
        }
        //校验密码
        String password = DigestUtils.md5DigestAsHex((params.getPassword() + user.getSalt()).getBytes());
        if (!user.getPassword().equals(password)) {
            throw new ValidateFailedException("密码不正确");
        }
        //生成token
        String token = TokenUtils.getToken(user);
        //生成登录日志
        UmsUserLoginLog loginLog = new UmsUserLoginLog();
        loginLog.setUserId(user.getId());
        loginLog.setLoginDate(new Date());
        loginLog.setToken(token);
        loginLog.setIp(IpUtils.getIpAddr(request));//获取访问ip地址
        loginLog.setType((byte) 1);//设置登录方式（1：pc）
        //添加到消息队列处理登录日志
        rabbitTemplate.convertAndSend("login.queue", loginLog);
        return token;
    }

    @Override
    @Transactional
    public String register(UmsRegisterParams params) throws Exception {
        //注册验证
        if (userInfoService.getUserInfoByUsername(params.getUsername()) != null) {
            throw new ValidateFailedException("用户名已存在");
        }
        if (getUserByEmail(params.getEmail()) != null) {
            throw new ValidateFailedException("邮箱已被注册");
        }
        UmsUser user = new UmsUser();
        String salt = RandomStringUtils.random(5);//生成随机字符串盐
        user.setSalt(salt);
        //加密密码
        user.setPassword(DigestUtils.md5DigestAsHex((params.getPassword() + salt).getBytes()));
        user.setEmail(params.getEmail());
        user.setStatus((byte) 1);
        user.setCreatedDate(new Date());
        userMapper.insertSelective(user);

        //生成用户详细信息
        UmsUserInfo userInfo = new UmsUserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setUsername(params.getUsername());
        userInfo.setCreatedDate(new Date());
        userInfo.setUpdatedDate(new Date());
        userInfoMapper.insertSelective(userInfo);
        //返回登录token
        return TokenUtils.getToken(user);
    }

    @Override
    @Cacheable(key = "#id")
    public UmsUser getUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public UmsUser getUserByEmail(String email) {
        UmsUserExample example = new UmsUserExample();
        example.createCriteria().andEmailEqualTo(email);
        if (userMapper.selectByExample(example).size() == 0) {
            return null;
        } else {
            return userMapper.selectByExample(example).get(0);
        }
    }

}
