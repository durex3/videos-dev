package com.durex.videos.service;

import com.durex.videos.vo.BgmVo;
import java.util.List;

/**
 * @author gelong
 * @date 2019/8/27 13:24
 */
public interface BgmService {

    /**
     * 返回bgm list
     * @return List<Bgm>
     */
    List<BgmVo> getBgmList();
}
