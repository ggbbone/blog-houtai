package com.yzg.blog.portal.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yzg.blog.common.api.CommonResult;
import com.yzg.blog.common.exception.BadRequestException;
import com.yzg.blog.common.exception.BizException;
import com.yzg.blog.common.utils.RequestUtils;
import com.yzg.blog.dao.mbg.model.UmsUserInfo;
import com.yzg.blog.portal.security.RequiresRoles;
import com.yzg.blog.portal.annotation.validation.groups.Login;
import com.yzg.blog.portal.annotation.validation.groups.Register;
import com.yzg.blog.portal.controller.dto.FeedbackDTO;
import com.yzg.blog.portal.controller.dto.UserDTO;
import com.yzg.blog.portal.controller.dto.UserInfoDTO;
import com.yzg.blog.portal.interceptor.ThreadUser;
import com.yzg.blog.portal.service.UploadService;
import com.yzg.blog.portal.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yangzg
 */
@Validated
@RestController
@RequestMapping(value = "/user")
@Api(tags = "用户管理")
@Slf4j
public class UserController {
    @Resource
    UserService userService;
    @Resource
    UploadService uploadService;

    @ApiOperation("github第三方授权登陆回调")
    @RequestMapping(value = "/auth/github", method = RequestMethod.GET)
    public void callbackGithub(@RequestParam("code") String code, HttpServletResponse response) throws IOException {
        //获取授权码 -> 请求令牌
        Map<String, Object> params = new HashMap<>();
        params.put("client_id", "1631605e4567752ef775");
        params.put("client_secret", "df9866ad2d38a9d982159a075b2d9c7cc0e9cd0d");
        params.put("code", code);
        String accessToken = HttpUtil.post(
                "https://github.com/login/oauth/access_token", params);
        accessToken = "token " + accessToken.split("&")[0].split("=")[1];
        response.sendRedirect("http://yangzg.xyz/oauth/github/" + accessToken);
    }

    @ApiOperation("github第三方授权登陆获取用户信息")
    @RequestMapping(value = "/auth/login", method = RequestMethod.GET)
    public CommonResult OAuthGithub(@RequestParam("token") String accessToken,@RequestParam String type) throws IOException {
        log.info(accessToken);
        //使用令牌获取用户数据
        long start = System.currentTimeMillis();
        String user = "";
        try {
            user = HttpRequest.get("https://api.github.com/user")
                    .header("Authorization", accessToken)//头信息，多个头信息多次调用此方法即可
                    .timeout(10000).execute().body();//超时，毫秒
        } catch (Exception e) {
            log.error("github第三方授权登陆获取用户信息失败", e);
            throw new BadRequestException("登陆失败，请稍后再试:" + e.getMessage());
        }
        log.info("github第三方授权登陆获取用户信息耗时：{}", System.currentTimeMillis() - start);
        JSONObject userJson = JSON.parseObject(user);
        String avatarUrl = String.valueOf(userJson.get("avatar_url"));
        String name = String.valueOf(userJson.get("name"));
        String id = String.valueOf(userJson.get("id"));
        String token = userService.OAuthGithub(id, name, avatarUrl);
        UmsUserInfo userInfo = userService.getUserInfoByToken(token);
        return CommonResult.success().addData("token", token)
                .addData("userInfo", userInfo);
    }


    @ApiOperation("获取总访问用户数")
    @RequestMapping(value = "/ips", method = RequestMethod.GET)
    public CommonResult getUserIps() {
        return CommonResult.success().addData("count", userService.getUserIps());
    }

    @ApiOperation("获取首页访问次数")
    @RequestMapping(value = "/requests", method = RequestMethod.GET)
    public CommonResult getRequests() {
        return CommonResult.success().addData("count", userService.getRequests());
    }

    @ApiOperation("文件上传")
    @RequiresRoles
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public CommonResult uploadFile(MultipartFile file) throws BizException {
        return CommonResult.success().addData("url", uploadService.uploadFile(file));
    }

    @ApiOperation("提交反馈")
    @RequestMapping(value = "/feedback", method = RequestMethod.POST)
    public CommonResult postFeedback(@RequestBody FeedbackDTO dto, HttpServletRequest request) throws BizException {
        dto.setIp(RequestUtils.getIpAddress(request));
        dto.setUserId(ThreadUser.get());
        userService.postFeedback(dto);
        return CommonResult.success();
    }

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    @ApiOperation("获取当前登录用户详细信息")
    @RequiresRoles
    @RequestMapping(value = "/session", method = RequestMethod.GET)
    public CommonResult getCurrentUserInfo() throws BizException {
        Integer userId = ThreadUser.get();
        UmsUserInfo userInfoById = userService.getUserInfoById(userId);
        return CommonResult.success().addData("userInfo", userInfoById);
    }

    /**
     * 用户登陆
     *
     * @return
     * @throws BizException
     */
    @ApiOperation("用户登陆")
    @RequestMapping(value = "/session", method = RequestMethod.POST)
    public CommonResult login(@RequestBody @Validated(Login.class) UserDTO params, HttpServletResponse response) throws BizException {
        String token = userService.login(params);
        UmsUserInfo userInfo = userService.getUserInfoByToken(token);
        return CommonResult.success()
                .addData("token", token)
                .addData("userInfo", userInfo);
    }

    /**
     * 注销登陆
     *
     * @return
     * @throws BizException
     */
    @ApiOperation("注销登陆")
    @RequiresRoles
    @RequestMapping(value = "/session", method = RequestMethod.DELETE)
    public CommonResult logout() throws BizException {
        return CommonResult.success();
    }

    @ApiOperation("用户注册")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public CommonResult register(@RequestBody @Validated(Register.class) UserDTO params) {
        String token = userService.register(params);
        return CommonResult.success().addData("token", token);
    }

    @ApiOperation("修改用户信息")
    @RequiresRoles("user")
    @RequestMapping(value = "/info", method = RequestMethod.PUT)
    public CommonResult updateUserInfo(@RequestBody UserInfoDTO params) {
        return CommonResult.success();
    }

    @ApiOperation("获取单个用户详细信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult getUserInfo(@PathVariable Integer id) throws BizException {
        UmsUserInfo userInfoById = userService.getUserInfoById(id);
        return CommonResult.success().addData("userInfo", userInfoById);
    }

}
