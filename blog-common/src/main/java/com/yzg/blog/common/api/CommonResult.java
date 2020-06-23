package com.yzg.blog.common.api;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用返回对象
 */
@Data
public class CommonResult {
    private int status;
    private String message;
    Map<String, Object> data = new HashMap<String, Object>();

    protected CommonResult() {
    }

    protected CommonResult(int code, String message) {
        this.status = code;
        this.message = message;
    }

    /**
     * 添加响应参数
     * @param key
     * @param value
     * @return
     */
    public CommonResult addData(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public CommonResult addPageData(Object o) {
        this.data.put("items", o);
        return this;
    }


    /**
     * 成功返回结果
     *
     */
    public static  CommonResult success() {
        return new CommonResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
    }
    /**
     * 成功返回结果
     *
     */
    public static  CommonResult success(String message) {
        return new CommonResult(ResultCode.SUCCESS.getCode(), message);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     */
    public static  CommonResult failed(IErrorCode errorCode) {
        return new CommonResult(errorCode.getCode(), errorCode.getMessage());
    }

    /**
     * 失败返回结果
     *
     * @param message 提示信息
     */
    public static  CommonResult failed(String message) {
        return new CommonResult(ResultCode.FAILED.getCode(), '['+ResultCode.FAILED.getMessage()+']'+message);
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
        return new CommonResult(ResultCode.BAD_REQUEST.getCode(), message);
    }


    /**
     * 未登录返回结果
     */
    public static  CommonResult failedUnauthorized() {
        return new CommonResult(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage());
    }

    /**
     * 未登录返回结果
     */
    public static  CommonResult failedUnauthorized(String message) {
        return new CommonResult(ResultCode.UNAUTHORIZED.getCode(), message);
    }

    /**
     * 未授权返回结果
     */
    public static  CommonResult failedForbidden() {
        return new CommonResult(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage());
    }

    /**
     * 失败返回结果
     * @param code
     * @param message
     * @return
     */
    public static CommonResult failed(int code, String message) {
        return new CommonResult(code, message);
    }

    /**
     * 失败返回结果
     * @param resultCode
     * @return
     */
    public static CommonResult failed(ResultCode resultCode) {
        return new CommonResult(resultCode.getCode(), resultCode.getMessage());
    }
}
