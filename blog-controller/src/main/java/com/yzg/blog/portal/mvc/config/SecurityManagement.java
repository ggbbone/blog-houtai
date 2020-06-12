package com.yzg.blog.portal.mvc.config;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangzg
 */
public class SecurityManagement {

    /**
     * 获取无需验证的权限
     * @return
     */
    public List<String> getDischargedAuths() {
        List<String> auths = new ArrayList<>();

        return auths;
    }

    /**
     * 获取用户拥有的权限
     * @return
     */
    public List<String> getAuthsByUserKey(Integer key) {
        List<String> auths = new ArrayList<>();

        return auths;
    }
}
