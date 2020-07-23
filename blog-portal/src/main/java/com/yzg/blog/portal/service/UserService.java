package com.yzg.blog.portal.service;


import com.yzg.blog.dao.mbg.model.UmsUser;
import com.yzg.blog.dao.mbg.model.UmsUserInfo;
import com.yzg.blog.portal.controller.dto.FeedbackDTO;
import com.yzg.blog.portal.controller.dto.UserDTO;

/**
 * @author yangzg
 */
public interface UserService {

    UmsUser getUserById(Integer id);

    String register(UserDTO dto);

    UmsUser getUserByEmail(String email);

    String login(UserDTO dto);

    UmsUserInfo getUserInfoById(Integer id);

    UmsUserInfo getUserInfoByToken(String token);

    Long getUserIps();

    Long getRequests();

    void postFeedback(FeedbackDTO dto);

    String OAuthGithub(String id, String name, String avatarUrl);

}
