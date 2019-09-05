package com.durex.videos.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author gelong
 * @date 2019/9/3 20:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideosVo {

    private String id;

    /**
     * 发布者id
     */
    private String userId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String faceImage;

    /**
     * 用户使用音频的信息
     */
    private String audioId;

    /**
     * 视频描述
     */
    private String videoDesc;

    /**
     * 视频存放的路径
     */
    private String videoPath;

    /**
     * 视频秒数
     */
    private Float videoSeconds;

    /**
     * 视频宽度
     */
    private Integer videoWidth;

    /**
     * 视频高度
     */
    private Integer videoHeight;

    /**
     * 视频封面图
     */
    private String coverPath;

    /**
     * 喜欢/赞美的数量
     */
    private Long likeCounts;

    /**
     * 创建时间
     */
    private Date createTime;
}
