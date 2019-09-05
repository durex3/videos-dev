package com.durex.videos.enums;

/**
 * @author gelong
 * @date 2019/8/23 16:05
 */
public enum ResponseErrorEnum {

    /**
     * 成功
     */
    SUCCESS(200),

    /**
     * 服务器异常
     */
    SERVER_FAIL(500),

    /**
     * token 拦截错误
     */
    TOKEN_FAIL(555);


    private Integer status;

    ResponseErrorEnum(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}
