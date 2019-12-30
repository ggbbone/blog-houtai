package com.yzg.blog.portal.exception;

/**
 * Created by yzg on 2019/12/30
 *
 * 用于404错误（参数异常）
 */
public class ValidateFailedException extends Exception{
    public ValidateFailedException(String message) {
        super(message);
    }
}
