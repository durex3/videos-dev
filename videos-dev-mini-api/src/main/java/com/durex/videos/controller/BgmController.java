package com.durex.videos.controller;

import com.durex.videos.service.BgmService;
import com.durex.videos.utils.JsonData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author gelong
 * @date 2019/8/27 16:52
 */
@RequestMapping("/bgms")
@RestController
public class BgmController {

    @Resource
    private BgmService bgmService;

    @GetMapping
    public JsonData getBgmList() {
        return JsonData.success(bgmService.getBgmList());
    }
}
