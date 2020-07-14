package com.yzg.blog.portal.service.impl;

import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import com.yzg.blog.portal.config.UploadConfig;
import com.yzg.blog.portal.service.UploadService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UploadServiceImpl implements UploadService {
    @Autowired
    UploadConfig config;

    /**
     * 华南区Region.region2(
     */
    private final Configuration configuration = new Configuration(Region.region2());

    private final UploadManager uploadManager = new UploadManager(configuration);

    @Override
    public String uploadFile(MultipartFile file) {
        Auth auth = Auth.create(config.getAccessKey(), config.getSecretKey());
        String token = auth.uploadToken(config.getBucket());
        try {
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null) {
                log.error("文件上传失败：file.getOriginalFilename() == null");
                return null;
            }
            String suffix = originalFilename.substring(originalFilename.lastIndexOf('.'));
            String filename = System.currentTimeMillis() + suffix;
            Response response = uploadManager.put(file.getInputStream(), filename, token, null, null);
            return config.getDomain() + "/" + filename + "-default";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
