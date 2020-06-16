package com.yzg.blog.demo.api;

import lombok.Data;

/**
 * 通用返回对象
 */
@Data
public class CommonResult {
    private int status;
    private String message;
    private Object data;

    protected CommonResult() {
    }

    protected CommonResult(int code, String message, Object data) {
        this.status = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     */
    public static  CommonResult success() {
        return new CommonResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }
    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static  CommonResult success(Object data) {
        return new CommonResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data    获取的数据
     * @param message 提示信息
     */
    public static  CommonResult success(Object data, String message) {
        return new CommonResult(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     */
    public static  CommonResult failed(IErrorCode errorCode) {
        return new CommonResult(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     *
     * @param message 提示信息
     */
    public static  CommonResult failed(String message) {
        return new CommonResult(ResultCode.FAILED.getCode(), '['+ResultCode.FAILED.getMessage()+']'+message, null);
    }

    /**
     * 失败返回结果
     */
    public static  CommonResult failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static  CommonResult failedValidate() {
        return failed(ResultCode.BAD_REQUEST);
    }
    /**
     * 参数校验失败返回结果
     * @param message 信息
     * @return
     */
    public static  CommonResult failedValidate(String message) {
        return new CommonResult(ResultCode.BAD_REQUEST.getCode(), '['+ResultCode.BAD_REQUEST.getMessage()+']'+message, null);
    }

    /**
     * 参数校验失败返回结果
     * @param message 信息
     * @return
     */
    public static  CommonResult failedValidate(String message, Object data) {
        return new CommonResult(ResultCode.BAD_REQUEST.getCode(), '['+ResultCode.BAD_REQUEST.getMessage()+']'+message, data);
    }

    /**
     * 参数校验失败返回结果
     * @param t 详细信息
     * @return
     */
    public static  CommonResult failedValidate(Object t) {
        return new CommonResult(ResultCode.BAD_REQUEST.getCode(), ResultCode.BAD_REQUEST.getMessage(), t);
    }

    /**
     * 未登录返回结果
     */
    public static  CommonResult failedUnauthorized() {
        return new CommonResult(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), null);
    }
    /**
     * 未登录返回结果
     */
    public static  CommonResult failedUnauthorized(Object data) {
        return new CommonResult(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static  CommonResult failedForbidden() {
        return new CommonResult(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), null);
    }
    /**
     * 未授权返回结果
     */
    public static  CommonResult failedForbidden(Object data) {
        return new CommonResult(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }

    public static CommonResult failed(int code, String message) {
        return new CommonResult(code, message, null);
    }

    public static CommonResult failed(ResultCode resultCode) {
        return new CommonResult(resultCode.getCode(), resultCode.getMessage(), null);
    }
}
