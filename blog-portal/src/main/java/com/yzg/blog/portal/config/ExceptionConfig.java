package com.yzg.blog.portal.config;

import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.portal.exception.ForbiddenException;
import com.yzg.blog.portal.exception.UnauthorizedException;
import com.yzg.blog.portal.exception.ValidateFailedException;
import com.yzg.blog.portal.model.Violation;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;

/**
 * Created by yzg on 2019/12/20
 *
 * 全局异常处理器
 */

@ControllerAdvice
public class ExceptionConfig {

    /**
     * 处理参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public CommonResult constraintViolationExceptionHandler(ConstraintViolationException e) {
        ArrayList<Violation> errors = new ArrayList<>();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            errors.add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return CommonResult.validateFailed(errors);
    }

    /**
     * 处理参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public CommonResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ArrayList<Violation> errors = new ArrayList<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errors.add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return CommonResult.validateFailed(errors);
    }

    /**
     * 处理参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public CommonResult bindExceptionHandler(BindException e) {
        ArrayList<Violation> errors = new ArrayList<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errors.add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return CommonResult.validateFailed(errors);
    }

    /**
     * 401错误（token无效）
     * @param e
     * @return
     */
    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public CommonResult unauthorizedExceptionHandle(UnauthorizedException e) {
        return CommonResult.unauthorized(e.getMessage());
    }

    /**
     * 403错误（权限不足）
     * @param e
     * @return
     */
    @ExceptionHandler(value = ForbiddenException.class)
    @ResponseBody
    public CommonResult forbiddenExceptionHandle(ForbiddenException e) {
        return CommonResult.forbidden(e.getMessage());
    }

    /**
     * 404错误（参数异常）
     * @param e
     * @return
     */
    @ExceptionHandler(value = ValidateFailedException.class)
    @ResponseBody
    public CommonResult validateFailedExceptionHandle(ValidateFailedException e) {
        return CommonResult.validateFailed(e.getMessage());
    }

    /**
     * sql错误
     * @param e
     * @return
     */
    @ExceptionHandler(value = DataAccessException.class)
    @ResponseBody
    public CommonResult  DataAccessExceptionHandle(DataAccessException e) {
        return CommonResult.validateFailed(e.getMessage());
    }
}
