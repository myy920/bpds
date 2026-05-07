package com.myy.bpds.common.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 分页结果类
 */
@Data
public class PageResult<T> {
    /**
     * 当前页码
     */
    private Long pageNum;

    /**
     * 每页条数
     */
    private Long pageSize;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 总页数
     */
    private Long pages;

    /**
     * 数据列表
     */
    private List<T> list;

    public PageResult(Long pageNum, Long pageSize, Long total, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
        this.pages = (total + pageSize - 1) / pageSize;
    }

    /**
     * 创建分页结果
     */
    public static <T> PageResult<T> of(Long pageNum, Long pageSize, Long total, List<T> list) {
        return new PageResult<>(pageNum, pageSize, total, list);
    }

    /**
     * 创建空分页结果
     */
    public static <T> PageResult<T> empty(Long pageNum, Long pageSize) {
        return new PageResult<>(pageNum, pageSize, 0L, List.of());
    }

    /**
     * 从 JPA Page 对象创建分页结果
     */
    public static <T> PageResult<T> of(Page<T> page) {
        return new PageResult<>((long) page.getNumber() + 1, // JPA 页码从0开始，转换为从1开始
                (long) page.getSize(), page.getTotalElements(), page.getContent());
    }
}
