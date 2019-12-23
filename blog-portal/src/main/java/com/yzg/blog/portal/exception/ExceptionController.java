package com.yzg.blog.portal.exception;

import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.portal.model.Violation;
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
 * 用于处理参数校验产生的异常
 */

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public CommonResult constraintViolationExceptionHandler(ConstraintViolationException e) {
        ArrayList<Violation> errors = new ArrayList<>();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            errors.add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return CommonResult.validateFailed(errors);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseBody
    public CommonResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ArrayList<Violation> errors = new ArrayList<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errors.add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return CommonResult.validateFailed(errors);
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public CommonResult bindExceptionHandler(BindException e) {
        ArrayList<Violation> errors = new ArrayList<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errors.add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return CommonResult.validateFailed(errors);
    }
}
