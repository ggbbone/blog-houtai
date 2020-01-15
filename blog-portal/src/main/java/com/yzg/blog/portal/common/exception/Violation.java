package com.yzg.blog.portal.common.exception;

/**
 * Created by yzg on 2019/12/23
 * 参数校验异常返回信息实体类
 */
public class Violation {
    //异常参数名称
    private final String fieldName;
    //异常提示信息
    private final String message;

    @Override
    public String toString() {
        return "Violation{" +
                "fieldName='" + fieldName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }

    public Violation(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }
}
