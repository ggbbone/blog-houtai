package com.yzg.blog.common.api;

/**
 * 常用API操作码
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "success"),
    FAILED(500, "error"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "未登录"),
    FORBIDDEN(403, "没有相关权限");
    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}