package com.yzg.blog.portal.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "qiniu")
@Data
public class UploadConfig {

    private String domain;

    private String accessKey;

    private String secretKey;

    private String bucket;

}
