package com.yzg.blog.portal.utils;

import com.yzg.blog.model.UmsUser;

/**
 * Created by yzg on 2019/12/27
 *
 * 当前登录用户,用于全局调用
 */
public class CurrentUser {
    private final static ThreadLocal<UmsUser> USER_THREAD_LOCAL = new ThreadLocal<>();

    public static void set(UmsUser user) {
        USER_THREAD_LOCAL.set(user);
    }

    public static UmsUser get() {
        return USER_THREAD_LOCAL.get();
    }

    public static void remove() {
        USER_THREAD_LOCAL.remove();
    }
}
