package com.yzg.blog.common.api;

/**
 * 常用API操作码
 */
public enum ResultCode implements IErrorCode {
    //常用API操作码
    SUCCESS(200, "success"),
    FAILED(500, "系统繁忙，请稍后重试"),
    METHOD_NOT_ALLOWED(405, "不支持的请求方法"),
    NOT_FOUND(404, "路径不存在"),
    UNAUTHORIZED(401, "未登录"),
    FORBIDDEN(403, "权限不足"),
    BAD_REQUEST(400, "错误的请求");


    private final int code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public static String getMessageByCode(int code) {
        ResultCode[] values = ResultCode.values();
        for (ResultCode bizErrorCode : values) {
            if (bizErrorCode.getCode() == code) {
                return bizErrorCode.getMessage();
            }
        }
        return null;
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