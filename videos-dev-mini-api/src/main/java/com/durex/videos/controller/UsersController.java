package com.durex.videos.controller;

import com.durex.videos.dto.UsersDto;
import com.durex.videos.enums.ResponseErrorEnum;
import com.durex.videos.service.UsersService;
import com.durex.videos.utils.JsonData;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author gelong
 * @date 2019/8/24 0:08
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    @Resource
    private UsersService usersService;
    @Value("${file-root-path}")
    private String fileRootPath;
    @Value("${face-relative-path}")
    private String faceRelativePath;

    /**
     * 用户注册
     * @param usersDto 前端参数
     * @return JsonData
     */
    @PostMapping
    public JsonData register(@RequestBody UsersDto usersDto) {
        return JsonData.success(usersService.createUser(usersDto));
    }

    /**
     * 用户登陆
     * @param usersDto 前端参数
     * @return JsonData
     */
    @PostMapping("/token")
    public JsonData login(@RequestBody UsersDto usersDto) {
        return JsonData.success(usersService.login(usersDto));
    }

    /**
     * 用户注销登陆
     * @param id token key
     * @return JsonData
     */
    @DeleteMapping("/token/{id}")
    public JsonData logout(@PathVariable String id) {
        usersService.logout(id);
        return JsonData.success();
    }

    /**
     * 用户上传头像
     * @param id 用户id
     * @param files 文件
     * @return JsonData
     */
    @PostMapping("/{id}/face")
    public JsonData upload(@PathVariable String id, @RequestParam(value = "files") MultipartFile []files) {
        if (files == null || files.length < 1) {
            return JsonData.fail(ResponseErrorEnum.SERVER_FAIL.getStatus(), "文件不能为空");
        }
        // 保存到数据库中的相对路径
        String uploadPath = faceRelativePath + id + "/";
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            String filename = files[0].getOriginalFilename();
            if (StringUtils.isNotBlank(filename)) {
                // 设置数据库保存的路径
                uploadPath += filename;
                // 文件上传的最终保存路径
                String finalFacePath = fileRootPath + uploadPath;
                File outFile = new File(finalFacePath);
                if (!outFile.getParentFile().exists()) {
                    // 创建父文件夹
                    if (!outFile.getParentFile().mkdirs()) {
                        return JsonData.fail(ResponseErrorEnum.SERVER_FAIL.getStatus(), "文件上传出错");
                    }
                }
                inputStream = files[0].getInputStream();
                fileOutputStream = new FileOutputStream(outFile);
                IOUtils.copy(inputStream, fileOutputStream);
                fileOutputStream.flush();
            } else {
                return JsonData.fail(ResponseErrorEnum.SERVER_FAIL.getStatus(), "文件不能为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.fail(ResponseErrorEnum.SERVER_FAIL.getStatus(), "文件上传出错");
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
        usersService.updateUserInfo(id, UsersDto.builder().faceImage(uploadPath).build());
        return JsonData.success(uploadPath);
    }

    /**
     * 查询用户信息
     * @param userId 用户id
     * @return JsonData
     */
    @GetMapping("/{userId}")
    public JsonData getUserInfo(@PathVariable String userId) {
        return JsonData.success(usersService.getUserInfo(userId));
    }

    /**
     * 修改用户信息
     * @param userId 用户id
     * @param usersDto 前端传参
     * @return JsonData
     */
    @PutMapping("/{userId}")
    public JsonData putUserInfo(@PathVariable String userId, @RequestBody UsersDto usersDto) {
        return JsonData.success();
    }

}
