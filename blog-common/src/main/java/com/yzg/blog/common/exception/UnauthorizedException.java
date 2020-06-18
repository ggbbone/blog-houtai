package com.yzg.blog.common.exception;

/**
 * @author yangzg
 *
 * 未登录异常
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message){
        super(message);
    }
}
