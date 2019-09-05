package com.durex.videos.mapper;

import com.durex.videos.utils.MyMapper;
import com.durex.videos.pojo.Videos;
import com.durex.videos.vo.VideosVo;
import java.util.List;

/**
 * @author gelong
 */
public interface VideosMapper extends MyMapper<Videos> {

    /**
     * 返回视频列表
     * @return List<VideosVo>
     */
    List<VideosVo> getVideoList();
}