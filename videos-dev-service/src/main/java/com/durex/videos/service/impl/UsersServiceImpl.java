package com.durex.videos.service.impl;

import com.durex.videos.dto.UsersDto;
import com.durex.videos.exception.VideosException;
import com.durex.videos.mapper.UsersMapper;
import com.durex.videos.pojo.Users;
import com.durex.videos.service.UsersService;
import com.durex.videos.utils.BeanValidator;
import com.durex.videos.utils.RedisOperator;
import com.durex.videos.vo.UsersVo;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author gelong
 * @date 2019/8/24 0:39
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Resource
    private UsersMapper usersMapper;
    @Resource
    private Sid sid;
    @Resource
    private RedisOperator redisOperator;
    private static final String USER_TOKEN_PREFIX = "user";


    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public UsersVo createUser(UsersDto usersDto) {
        BeanValidator.check(usersDto);
        Users users = Users.builder()
                .username(usersDto.getUsername())
                .build();
        if (null != usersMapper.selectOne(users)) {
            throw new VideosException("用户名已存在");
        }
        users.setId(sid.nextShort());
        users.setUsername(usersDto.getUsername());
        users.setPassword(usersDto.getPassword());
        users.setNickname(usersDto.getUsername());
        users.setFaceImage(null);
        users.setFansCounts(0);
        users.setFollowCounts(0);
        users.setReceiveLikeCounts(0);
        usersMapper.insert(users);
        users = usersMapper.selectByPrimaryKey(users.getId());
        UsersVo usersVo = UsersVo.builder().build();
        BeanUtils.copyProperties(users, usersVo);
        setUserToken(usersVo);
        return usersVo;
    }

    @Override
    public UsersVo login(UsersDto usersDto) {
        BeanValidator.check(usersDto);
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", usersDto.getUsername());
        criteria.andEqualTo("password", usersDto.getPassword());
        UsersVo usersVo = UsersVo.builder().build();
        Users users = usersMapper.selectOneByExample(example);
        if (null == users) {
            throw new VideosException("用户名或密码错误");
        }
        BeanUtils.copyProperties(users, usersVo);
        setUserToken(usersVo);
        return usersVo;
    }

    @Override
    public void logout(String id) {
        redisOperator.del(USER_TOKEN_PREFIX + ":" + id);
    }

    @Override
    public void updateUserInfo(String userId, UsersDto usersDto) {
        Users users = Users.builder().build();
        usersDto.setUsername(null);
        usersDto.setPassword(null);
        BeanUtils.copyProperties(usersDto, users);
        users.setId(userId);
        usersMapper.updateByPrimaryKeySelective(users);
    }

    @Override
    public UsersVo getUserInfo(String id) {
        Users users = usersMapper.selectByPrimaryKey(id);
        UsersVo usersVo = UsersVo.builder().build();
        BeanUtils.copyProperties(users, usersVo);
        return usersVo;
    }

    /**
     * 设置用户的token
     * @param usersVo 用户信息vo
     */
    private void setUserToken(UsersVo usersVo) {
        String token = UUID.randomUUID().toString();
        redisOperator.set(USER_TOKEN_PREFIX + ":" + usersVo.getId(), token, 1000 * 60 * 30);
        usersVo.setToken(token);
    }
}
