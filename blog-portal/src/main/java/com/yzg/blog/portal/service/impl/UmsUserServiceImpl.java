package com.yzg.blog.portal.service.impl;

import com.yzg.blog.mapper.UmsUserInfoMapper;
import com.yzg.blog.mapper.UmsUserLoginLogMapper;
import com.yzg.blog.model.UmsUserInfo;
import com.yzg.blog.model.UmsUserInfoExample;
import com.yzg.blog.model.UmsUserLoginLog;
import com.yzg.blog.portal.dto.UmsLoginParams;
import com.yzg.blog.portal.dto.UmsRegisterParams;
import com.yzg.blog.portal.exception.ValidateFailedException;
import com.yzg.blog.portal.service.UmsUserService;
import com.yzg.blog.portal.utils.IpUtils;
import com.yzg.blog.portal.utils.TokenUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by yzg on 2019/12/24
 */
@Service
@CacheConfig(cacheNames = {"CACHE::USER"})
public class UmsUserServiceImpl implements UmsUserService {
    @Autowired
    private UmsUserInfoMapper userInfoMapper;
    @Autowired
    private UmsUserLoginLogMapper userLoginLogMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Override
    public String getLoginCode(String name) {
        return null;
    }

    @Override
    public String login(UmsLoginParams params, HttpServletRequest request) throws Exception {
        UmsUserInfo user;
        //通过用户名登录
        user = getUserByUsername(params.getUsername());
        if (user == null){
            //用户名不存在时，使用邮箱登录
            user = getUserByEmail(params.getUsername());
        }
        if (user == null){
            throw new ValidateFailedException("用户名/邮箱不存在");
        }
        //md5加密密码
        String password = DigestUtils.md5DigestAsHex((params.getPassword() + user.getSalt()).getBytes());
        if (!user.getPassword().equals(password)) {
            throw new ValidateFailedException("密码不正确");
        }
        //返回token
        String token = TokenUtils.getToken(user);
        //写入登录记录
        UmsUserLoginLog loginLog = new UmsUserLoginLog();
        loginLog.setUserId(user.getId());
        loginLog.setLoginDate(new Date());
        loginLog.setToken(token);
        loginLog.setIp(IpUtils.getIpAddr(request));//获取访问ip地址
        loginLog.setType((byte) 1);//设置登录方式（1：pc）
        //添加到消息队列
        rabbitTemplate.convertAndSend("login.queue", loginLog);
        return token;
    }

    /**
     * 根据id查询用户，有缓存
     * @param id 用户id
     * @return 用户数据
     */
    @Override
    @Cacheable(key = "#id")
    public UmsUserInfo getUserById(int id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public String register(UmsRegisterParams params) throws Exception {
        //注册验证
        if (getUserByUsername(params.getUsername()) != null) {
            throw new ValidateFailedException("用户名已存在");
        }
        if (getUserByEmail(params.getEmail()) != null) {
            throw new ValidateFailedException("邮箱已被注册");
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
