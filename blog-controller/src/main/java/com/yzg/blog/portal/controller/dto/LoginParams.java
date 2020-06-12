package com.yzg.blog.portal.controller.dto;

import lombok.Data;

/**
 * @author yangzg
 */
@Data
public class LoginParams {

    private String username;
    private String password;
    private String code;
}
