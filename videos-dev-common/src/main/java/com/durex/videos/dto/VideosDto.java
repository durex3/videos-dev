package com.durex.videos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.NotNull;

/**
 * @author gelong
 * @date 2019/8/28 20:42
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideosDto {

    /**
     * bgm id
     */
    String bgmId;

    /**
     * 视频
     */
    @NotNull(message = "视频不能为空")
    MultipartFile file;

    /**
     * 视频秒数
     */
    Float videoSeconds;

    /**
     * 视频宽度
     */
    @NotNull(message = "视频宽度不能为空")
    private Integer videoWidth;

    /**
     * 视频高度
     */
    @NotNull(message = "视频高度不能为空")
    private Integer videoHeight;

    /**
     * 视频描述
     */
    private String videoDesc;

    /**
     * 视频封面图
     */
    private String coverPath;


}
