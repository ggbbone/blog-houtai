package com.yzg.blog.portal.service;

/**
 * Created by yzg on 2020/3/6
 */
public interface EmailService {
    /**
     * 发送邮件
     * @param subject 主题
     * @param text 内容
     */
    void sendSimpleEmail(String to, String subject, String text);
}
