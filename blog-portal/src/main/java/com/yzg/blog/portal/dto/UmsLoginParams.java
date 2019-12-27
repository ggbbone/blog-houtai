package com.yzg.blog.portal.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * Created by yzg on 2019/12/26
 *
 * 用户模块登录请求参数
 */
public class UmsLoginParams {
    @ApiModelProperty("登录名")
    @NotEmpty
    private String username;

    @ApiModelProperty("登录密码")
    @NotEmpty
    private String password;

    @ApiModelProperty("登录验证码")
    @NotEmpty
    private String code;

    @Override
    public String toString() {
        return "UmsLoginParams{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
