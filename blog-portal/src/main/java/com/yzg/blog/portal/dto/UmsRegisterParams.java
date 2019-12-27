package com.yzg.blog.portal.dto;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * Created by yzg on 2019/12/27
 *
 * 用户注册请求参数
 */
public class UmsRegisterParams {
    @ApiModelProperty("用户名")
    @Length(min = 4, max = 18, message = "用户名长度为4到18个字符")
    private String username;

    @ApiModelProperty("邮箱")
    @Email(message = "邮箱格式不正确")
    private String email;

    @ApiModelProperty("登录密码")
    @Length(min = 8, max = 24,message = "密码长度在8到24个字符")
    private String password;

    @ApiModelProperty("登录验证码")
    @NotEmpty
    private String code;

    @Override
    public String toString() {
        return "UmsRegisterParams{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
