package com.yzg.blog.portal.exception;

import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.common.api.ResultCode;
import com.yzg.blog.common.exception.BadRequestException;
import com.yzg.blog.common.exception.BizException;
import com.yzg.blog.common.exception.ForbiddenException;
import com.yzg.blog.common.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
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
        log.warn(e.getMessage());
        return CommonResult.failed(e.getCode(), e.getMessage());
    }

    /**
     * 404错误
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public CommonResult handleNoFoundException(NoHandlerFoundException e) {
        log.warn(e.getMessage());
        return CommonResult.failed(ResultCode.NOT_FOUND.getCode(),"路径不存在:" + e.getMessage());
    }

    /**
     * 未登录异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public CommonResult handleUnauthorizedException(UnauthorizedException e) {
        log.warn(e.getMessage());
        return CommonResult.failedUnauthorized(e.getMessage());
    }

    /**
     * 权限不足
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ForbiddenException.class)
    public CommonResult handleForbiddenException(ForbiddenException e) {
        log.warn(e.getMessage());
        return CommonResult.failedForbidden();
    }


    /**
     * 参数类型异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public CommonResult handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.warn("参数转换异常-方法:{},字段:{},参数:{},错误信息:{}", e.getParameter().getMethod(), e.getName(), e.getValue(), e.getMessage());
        return CommonResult.failedValidate("参数异常:" + e.getValue());
    }

    /**
     * 请求方法不支持
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public CommonResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.warn(e.getMessage());
        return CommonResult.failed(ResultCode.METHOD_NOT_ALLOWED.getCode(),"不支持的请求方法:" + e.getMethod());
    }

    /**
     * 请求参数异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public CommonResult handleBadRequestException(BadRequestException e) {
        log.warn(e.getMessage());
        return CommonResult.failedValidate(e.getMessage());
    }

    /**
     * 缺少请求参数
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public CommonResult handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.warn(e.getMessage());
        return CommonResult.failedValidate("缺少必填请求参数:" + e.getMessage());
    }

    /**
     * 方法参数校验失败
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn(e.getMessage());
        return CommonResult.failedValidate(Objects.requireNonNull(e.getBindingResult().getFieldError()).getField() + ":" + Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    /**
     * ValidationException
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public CommonResult handleValidationException(ValidationException e) {
        log.warn(e.getMessage());
        return CommonResult.failedValidate(e.getCause().getMessage());
    }

    /**
     * ConstraintViolationException
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public CommonResult handleConstraintViolationException(ConstraintViolationException e) {
        log.warn(e.getMessage());
        return CommonResult.failedValidate(e.getMessage().substring(e.getMessage().indexOf('.')));
    }


    /**
     * 数据库插入唯一重复
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
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
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public CommonResult handleException(Exception e) {
        log.error(e.getMessage(), e);
        return CommonResult.failed(e.getMessage());
    }

}

