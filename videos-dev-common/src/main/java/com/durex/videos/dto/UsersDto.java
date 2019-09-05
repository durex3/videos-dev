package com.durex.videos.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

/**
 * @author gelong
 * @date 2019/8/24 0:10
 */
@Data
@Builder
public class UsersDto {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 5, max = 10, message = "密码长度在5-10位之间")
    private String password;

    /**
     * 头像
     */
    private String faceImage;

    /**
     * 昵称
     */
    private String nickname;
}
