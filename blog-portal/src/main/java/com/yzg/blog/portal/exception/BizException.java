package com.yzg.blog.portal.exception;

import com.yzg.blog.common.api.IErrorCode;

/**
 * @author yangzg
 *
 * 自定义业务异常
 */
public class BizException extends RuntimeException implements IErrorCode {
    private static final long serialVersionUID = 1L;

    private final String msg;
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

    public BizException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
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
