package com.durex.videos.vo;

import lombok.Builder;
import lombok.Data;
import java.util.List;

/**
 * @author gelong
 * @date 2019/9/3 21:03
 */
@Data
@Builder
public class PageResult<T> {

    /**
     * 当前页
     */
    private Integer page;

    /**
     * 总页数
     */
    private Integer total;

    /**
     * 每页记录
     */
    private List<T> rows;
}
