package com.yzg.blog.common.exception;

/**
 * @author yangzg
 *
 * 权限不足
 */
public class ForbiddenException extends RuntimeException{
    public ForbiddenException(String message){
        super(message);
    }
}
