package com.durex.videos.beans;

import com.durex.videos.enums.ResponseErrorEnum;
import com.durex.videos.utils.JsonData;
import com.durex.videos.exception.ParamException;
import com.durex.videos.exception.VideosException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

/**
 * @author gelong
 * @date 2019/8/23 13:37
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Object handlerException(HttpServletRequest request, Exception e) {
        JsonData result = null;
        if (e instanceof VideosException || e instanceof ParamException) {
            result = JsonData.fail(ResponseErrorEnum.SERVER_FAIL.getStatus(), e.getMessage());
        } else {
            log.info("Exception:{}", e);
            result = JsonData.fail(ResponseErrorEnum.SERVER_FAIL.getStatus(),"system error");
        }
        return result;
    }
}
