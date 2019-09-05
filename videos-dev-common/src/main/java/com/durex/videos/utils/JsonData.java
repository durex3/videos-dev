package com.durex.videos.utils;

import com.durex.videos.enums.ResponseErrorEnum;
import lombok.Data;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gelong
 */
@Data
public class JsonData {

    private Integer status;

    private String msg;

    private Object data;

    public JsonData(Integer status) {
        this.status = status;
    }

    public static JsonData success(Object data, String msg) {
        JsonData jsonData = new JsonData(ResponseErrorEnum.SUCCESS.getStatus());
        jsonData.data = data;
        jsonData.msg = msg;
        return jsonData;
    }

    public static JsonData success(Object data) {
        JsonData jsonData = new JsonData(ResponseErrorEnum.SUCCESS.getStatus());
        jsonData.data = data;
        return jsonData;
    }

    public static JsonData success() {
        JsonData jsonData = new JsonData(ResponseErrorEnum.SUCCESS.getStatus());
        return jsonData;
    }

    public static JsonData fail(Integer status, String msg) {
        JsonData jsonData = new JsonData(status);
        jsonData.msg = msg;
        return jsonData;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", status);
        map.put("msg", msg);
        map.put("data", data);
        return map;
    }
}