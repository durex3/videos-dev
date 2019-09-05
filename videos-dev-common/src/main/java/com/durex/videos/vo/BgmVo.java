package com.durex.videos.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author gelong
 * @date 2019/8/27 18:49
 */
@Data
@Builder
public class BgmVo {
    private String id;

    private String author;

    private String name;

    /**
     * 播放地址
     */
    private String path;

}
