package com.durex.videos.service.impl;

import com.durex.videos.mapper.BgmMapper;
import com.durex.videos.pojo.Bgm;
import com.durex.videos.service.BgmService;
import com.durex.videos.vo.BgmVo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gelong
 * @date 2019/8/27 13:25
 */
@Service
public class BgmServiceImpl implements BgmService {

    @Resource
    private BgmMapper bgmMapper;

    @Override
    public List<BgmVo> getBgmList() {
        List<BgmVo> bgmVoList = bgmMapper.selectAll().stream().map(this :: buildBgmVo).collect(Collectors.toList());
        return bgmVoList;
    }

    private BgmVo buildBgmVo(Bgm bgm) {
        return BgmVo.builder()
                .id(bgm.getId())
                .author(bgm.getAuthor())
                .name(bgm.getName())
                .path(bgm.getPath())
                .build();
    }
}
