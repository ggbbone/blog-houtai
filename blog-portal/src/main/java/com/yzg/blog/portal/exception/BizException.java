package com.yzg.blog.portal.exception;

import com.yzg.blog.demo.api.IErrorCode;

/**
 * @author yangzg
 *
 * 自定义业务异常
 */
public class BizException extends RuntimeException implements IErrorCode {
    private static final long serialVersionUID = 1L;

    private String msg;
    private Integer code;

    public BizException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BizException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BizException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public BizException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }
    public BizException() {
        super();
    }

    public BizException(int code, String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public static BizException createInstance(int errorCode, String errorMsg) {
        return new BizException(errorCode, errorMsg);
    }

    public static BizException createInstance(IErrorCode iErrorCode) {
        return new BizException(iErrorCode.getCode(), iErrorCode.getMessage());
    }

    public static BizException createInstance(IErrorCode iErrorCode, Exception e) {
        return new BizException(iErrorCode.getCode(), iErrorCode.getMessage(), e);
    }

    public static BizException createInstance(IErrorCode iErrorCode, String errMsg) {
        return new BizException(iErrorCode.getCode(), "[" + iErrorCode.getMessage() + "]" + errMsg);
    }

    public static BizException createInstance(IErrorCode iErrorCode, String errMsg, Exception e) {
        return new BizException(iErrorCode.getCode(), "[" + iErrorCode.getMessage() + "]" + errMsg, e);
    }

    public static BizException createInstance(int errorCode, String errorMsg, Exception e) {
        return new BizException(errorCode, errorMsg, e);
    }

    @Override
    public int getCode() {
        return this.code;
    }
    @Override
    public String getMessage() {
        return this.msg;
    }
}
