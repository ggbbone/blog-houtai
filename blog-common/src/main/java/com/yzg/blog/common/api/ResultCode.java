package com.yzg.blog.common.api;

/**
 * 常用API操作码
 */
public enum ResultCode implements IErrorCode {
    //常用API操作码
    SUCCESS(200, "success"),
    FAILED(500, "error"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "未登录"),
    FORBIDDEN(403, "权限不足");
    private final int code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}