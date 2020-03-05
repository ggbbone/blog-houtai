package com.yzg.blog.portal.common.exception;

/**
 * Created by yzg on 2019/12/27
 *
 * 自定义异常，用于403错误（权限不足）
 */
public class ForbiddenException extends Exception {
    public ForbiddenException(String message) {
        super(message);
    }
}
