package com.durex.videos.service.impl;

import com.durex.videos.dto.VideosDto;
import com.durex.videos.exception.VideosException;
import com.durex.videos.mapper.VideosMapper;
import com.durex.videos.pojo.Videos;
import com.durex.videos.service.VideosService;
import com.durex.videos.utils.BeanValidator;
import com.durex.videos.vo.PageResult;
import com.durex.videos.vo.VideosVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author gelong
 * @date 2019/8/28 21:03
 */
@Service
public class VideosServiceImpl implements VideosService {

    @Value("${file-root-path}")
    private String fileRootPath;
    @Value("${videos-relative-path}")
    private String videosRelativePath;
    @Value("${videos-cover-path}")
    private String videosCoverPath;
    @Value("${max-video-seconds}")
    Float maxVideoSeconds;
    @Value("${min-video-seconds}")
    Float minVideoSeconds;
    @Resource
    private VideosMapper videosMapper;
    @Resource
    private Sid sid;

    @Override
    public void createVideo(String userId, VideosDto videosDto) {
        BeanValidator.check(videosDto);
        if (maxVideoSeconds < videosDto.getVideoSeconds() || minVideoSeconds  > videosDto.getVideoSeconds()) {
            throw new VideosException("视频长度非法");
        }
        String filename = videosDto.getFile().getOriginalFilename();
        if (StringUtils.isBlank(filename)) {
            throw new VideosException("视频非法");
        }
        // 设置数据库保存的路径
        String videoPath = videosRelativePath + userId + "/" + filename;
        try {
            uploadVideo(videosDto.getFile().getInputStream(), fileRootPath + videoPath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new VideosException("上传视频出错");
        }
        Videos videos = new Videos();
        BeanUtils.copyProperties(videosDto, videos);
        videos.setId(sid.nextShort());
        videos.setUserId(userId);
        videos.setVideoPath(videoPath);
        videos.setLikeCounts(0L);
        videos.setCoverPath(videosCoverPath);
        videos.setAudioId(null);
        videos.setCreateTime(new Date());
        videos.setStatus(1);
        videosMapper.insertSelective(videos);
    }

    /**
     * 上传视频
     * @param inputStream 文件输入流
     * @param path 文件存储绝对路径
     */
    private void uploadVideo(InputStream inputStream, String path) {
            // 设置数据库保存的路径
        File outFile = new File(path);
        if (!outFile.getParentFile().exists()) {
            // 创建父文件夹
            if (!outFile.getParentFile().mkdirs()) {
                throw new VideosException("上传视频出错");
            }
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(outFile);
            IOUtils.copy(inputStream, fileOutputStream);
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new VideosException("上传视频出错");
        } finally {
            try {
                if (null != fileOutputStream) {
                    fileOutputStream.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public PageResult<VideosVo> getVideoList(Integer page, Integer size) {
        if (page < 0) {
            page = 1;
        }
        if (size < 0) {
            size = 10;
        }
        PageHelper.startPage(page, size);
        List<VideosVo> list = videosMapper.getVideoList();
        PageInfo<VideosVo> pageInfo = new PageInfo<>(list);
        return PageResult.<VideosVo>builder()
                .page(page)
                .total(pageInfo.getPages())
                .rows(list)
                .build();
    }
}
