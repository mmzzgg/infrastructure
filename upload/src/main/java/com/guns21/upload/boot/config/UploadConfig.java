package com.guns21.upload.boot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jliu on 16/7/12.
 */
@Configuration
@ConfigurationProperties(prefix = "com.guns21.upload")
public class UploadConfig {

    public static String UPLOAD_ROOT_PATH;
    public static int UPLOAD_IMG_THUMBNAIL_MAX_PIXEL;

    public void setUploadRootPath(String uploadRootPath) {
        UPLOAD_ROOT_PATH = uploadRootPath;
    }

    public void setUploadJpgThumbnailMaxPixel(int uploadJpgThumbnailMaxPixel) {
        UPLOAD_IMG_THUMBNAIL_MAX_PIXEL = uploadJpgThumbnailMaxPixel;
    }
}
