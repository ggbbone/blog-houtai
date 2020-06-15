package com.yzg.blog.portal.controller.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author yangzg
 */
@Data
public class LoginParams {

    @ApiModelProperty(value = "用户名/邮箱")
    @NotBlank(message = "用户名/邮箱不能为空")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String code;
}
