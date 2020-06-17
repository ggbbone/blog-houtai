package com.yzg.blog.portal.controller.dto;

import com.yzg.blog.portal.annotation.validation.groups.ChangePassword;
import com.yzg.blog.portal.annotation.validation.groups.Login;
import com.yzg.blog.portal.annotation.validation.groups.Register;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author yangzg
 */
@Data
public class UserDTO {

    @ApiModelProperty(value = "用户名/邮箱")
    @NotBlank(message = "用户名/邮箱不能为空", groups = {Login.class, Register.class})
    private String username;

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
