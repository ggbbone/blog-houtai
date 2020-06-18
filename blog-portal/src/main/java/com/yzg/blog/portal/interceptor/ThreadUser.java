package com.yzg.blog.portal.interceptor;


/**
 * @author yangzg
 *
 * 与请求线程绑定的用户id缓存，用于整个请求过程中调用
 */
public class ThreadUser {
    private static final ThreadLocal<Integer> USER_ID = new ThreadLocal<>();

    public static void set(Integer u) {
        USER_ID.set(u);
    }

    public static Integer get() {
        return USER_ID.get();
    }

    public static void remove() {
        USER_ID.remove();
    }
}
