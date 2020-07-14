package com.yzg.blog.portal.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    /**
     * 上传文件
     * @param file
     * @return
     */
    String uploadFile(MultipartFile file);
}
