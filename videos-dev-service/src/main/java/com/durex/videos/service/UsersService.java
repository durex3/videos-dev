package com.durex.videos.service;

import com.durex.videos.dto.UsersDto;
import com.durex.videos.vo.UsersVo;

/**
 * @author gelong
 * @date 2019/8/24 0:25
 */
public interface UsersService {

    /**
     * 创建用户并返回用户信息
     * @param usersDto 用户dto
     * @return UsersVo 用户信息vo
     */
    UsersVo createUser(UsersDto usersDto);

    /**
     * 根据用户名密码返回用户信息
     * @param usersDto 登陆信息dto
     * @return UsersVo 用户信息vo
     */
    UsersVo login(UsersDto usersDto);

    /**
     * 删除redis中的用户token
     * @param id 前端传过来的id
     */
    void logout(String id);

    /**
     * 更新用户信息
     * @param userId 用户id
     * @param usersDto 用户信息dto
     */
    void updateUserInfo(String userId, UsersDto usersDto);

    /**
     * 根据id查询用户信息
     * @param id 用户id
     * @return UsersVo 用户信息vo
     */
    UsersVo getUserInfo(String id);

}
