package com.durex.videos.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author gelong
 * @date 2019/8/24 15:27
 */
@Data
@Builder
public class UsersVo {

    /**
     * id
     */
    private String id;

    /**
     * 我的头像
     */
    private String faceImage;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 我的粉丝数量
     */
    private Integer fansCounts;

    /**
     * 我关注的人总数
     */
    private Integer followCounts;

    /**
     * 我接受到的赞美/收藏 的数量
     */
    private Integer receiveLikeCounts;

    /**
     * 用户的token
     */
    private String token;

}
