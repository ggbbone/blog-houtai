package com.yzg.blog.common.exception;

/**
 * @author yangzg
 *
 * 请求参数异常
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message){
        super(message);
    }

}
