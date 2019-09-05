package com.durex.videos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author gelong
 * @date 2019/8/26 22:42
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${file-root-path}")
    private String fileRootPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("file:" + fileRootPath);
    }
}
