package com.yzg.blog.portal.controller.dto;

import com.yzg.blog.portal.annotation.validation.groups.ChangePassword;
import com.yzg.blog.portal.annotation.validation.groups.Login;
import com.yzg.blog.portal.annotation.validation.groups.Register;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author yangzg
 */
@Data
public class UserDTO {


    @ApiModelProperty(value = "注册邮箱")
    @NotBlank(message = "邮箱不能为空", groups = {Login.class, Register.class})
    @Email(message = "邮箱格式不正确")
    private String email;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空", groups = {Login.class, Register.class})
    private String password;

    @ApiModelProperty(value = "确认密码")
    @NotBlank(message = "确认密码不能为空", groups = {Register.class})
    private String confirmPassword;

    @ApiModelProperty(value = "旧密码")
    @NotBlank(message = "旧密码不能为空", groups = {ChangePassword.class})
    private String oldPassword;

    @ApiModelProperty(value = "验证码")
    private String code;
}
