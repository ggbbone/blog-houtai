package com.yzg.blog.portal.utils;

import com.yzg.blog.model.UmsUserInfo;

/**
 * Created by yzg on 2019/12/27
 *
 * 当前登录用户,用于全局调用
 */
public class CurrentUser {
    private final static ThreadLocal<UmsUserInfo> USER_INFO_THREAD_LOCAL = new ThreadLocal<>();

    public static void set(UmsUserInfo userInfo) {
        USER_INFO_THREAD_LOCAL.set(userInfo);
    }

    public static UmsUserInfo get() {
        return USER_INFO_THREAD_LOCAL.get();
    }

    public static void remove() {
        USER_INFO_THREAD_LOCAL.remove();
    }
}
