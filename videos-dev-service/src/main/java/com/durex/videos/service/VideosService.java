package com.durex.videos.service;

import com.durex.videos.dto.VideosDto;
import com.durex.videos.vo.PageResult;
import com.durex.videos.vo.VideosVo;

/**
 * @author gelong
 * @date 2019/8/28 21:02
 */
public interface VideosService {

    /**
     * 上传视频
     * @param userId 用户id
     * @param videosDto 视频信息dto
     */
    void createVideo(String userId, VideosDto videosDto);

    /**
     * 分页返回视频裂变
     * @param page 当前页
     * @param size 每页大小
     * @return List<Videos> 视频列表
     */
    PageResult<VideosVo> getVideoList(Integer page, Integer size);
}
