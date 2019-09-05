package com.durex.videos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author gelong
 * @date 2019/8/22 23:10
 */
@SpringBootApplication
@MapperScan(basePackages="com.durex.videos.mapper")
@ComponentScan(basePackages= {"com.durex.videos", "org.n3r.idworker"})
public class VideosApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideosApplication.class, args);
    }
}
