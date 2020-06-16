package com.yzg.blog.portal.exception;

import com.yzg.blog.common.api.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Objects;

/**
 * @author yangzg
 *
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class DefaultExceptionHandler {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BizException.class)
    public CommonResult handleBizException(BizException e) {
        log.error(e.getMessage(), e);
        return CommonResult.failed(e.getCode(), e.getMessage());
    }

    /**
     * url无法匹配接口方法
     * @param e
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public CommonResult handleNoFoundException(NoHandlerFoundException e) {
        log.error(e.getMessage(), e);
        return CommonResult.failedValidate("路径不存在:" + e.getMessage());
    }

    /**
     * 参数类型异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public CommonResult handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("参数转换异常-方法:{},字段:{},参数:{},错误信息:{}", e.getParameter().getMethod(), e.getName(), e.getValue(), e.getMessage());
        return CommonResult.failedValidate("参数异常:" + e.getValue());
    }

    /**
     * 请求方法不支持
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public CommonResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        return CommonResult.failedValidate("不支持的请求方法:" + e.getMethod());
    }

    /**
     * 缺少请求参数
     * @param e
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public CommonResult handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error(e.getMessage(), e);
        return CommonResult.failedValidate("缺少必填请求参数:" + e.getMessage());
    }

    /**
     * 方法参数校验失败
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return CommonResult.failedValidate(Objects.requireNonNull(e.getBindingResult().getFieldError()).getField() + ":" + Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    /**
     * ValidationException
     */
    @ExceptionHandler(ValidationException.class)
    public CommonResult handleValidationException(ValidationException e) {
        log.error(e.getMessage(), e);
        return CommonResult.failedValidate(e.getCause().getMessage());
    }

    /**
     * ConstraintViolationException
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public CommonResult handleConstraintViolationException(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        return CommonResult.failedValidate(e.getMessage());
    }


    /**
     * 数据库插入唯一重复
     * @param e
     * @return
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public CommonResult handleDuplicateKeyException(DuplicateKeyException e) {
        log.error(e.getMessage(), e);
        return CommonResult.failed("数据重复，请检查后提交");
    }

    /**
     * 其他异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public CommonResult handleException(Exception e) {
        log.error(e.getMessage(), e);
        return CommonResult.failed(e.getMessage());
    }

}

