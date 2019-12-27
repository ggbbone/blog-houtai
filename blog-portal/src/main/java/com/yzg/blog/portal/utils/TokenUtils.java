package com.yzg.blog.portal.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.yzg.blog.model.UmsUserInfo;

import java.util.Date;


/**
 * Created by yzg on 2019/12/27
 *
 * 用户token相关工具类
 */
public class TokenUtils {

    /**
     * 根据用户信息生成token
     * @param user
     * @return
     */
    public static String getToken(UmsUserInfo user) {
        return JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + 7 *24 * 3600 * 1000))//设置过期时间7天
                .withAudience(String.valueOf(user.getId()))
                .sign(Algorithm.HMAC256(user.getPassword()));
    }


}
