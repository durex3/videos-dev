package com.durex.videos.controller;

import com.durex.videos.dto.VideosDto;
import com.durex.videos.service.VideosService;
import com.durex.videos.utils.JsonData;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;


/**
 * @author gelong
 * @date 2019/8/28 20:34
 */
@RestController
public class VideosController {

    @Resource
    private VideosService videosService;

    @PostMapping("/users/{userId}/videos")
    public JsonData upload(@PathVariable String userId, VideosDto videosDto) {
        videosService.createVideo(userId, videosDto);
        return JsonData.success();
    }

    @GetMapping("/videos")
    public JsonData getVideoList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "size", defaultValue = "5") Integer size) {
        return JsonData.success(videosService.getVideoList(page, size));
    }
}
