package com.yzg.blog.portal.exception;

/**
 * Created by yzg on 2019/12/27
 *
 * 自定义异常，用于401错误（token无效）
 */
public class UnauthorizedException extends Exception {
    public UnauthorizedException(String message) {
        super(message);
    }
}
