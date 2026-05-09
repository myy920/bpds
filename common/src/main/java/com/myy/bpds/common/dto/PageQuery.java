package com.myy.bpds.common.dto;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * 分页查询参数
 */
@Data
public class PageQuery {

    /**
     * 页码，从1开始
     */
    @NotNull(message = "pageNum 不能为空")
    @Min(value = 1, message = "pageNum 从1开始")
    private Integer pageNum;

    /**
     * 每页大小
     */
    @NotNull(message = "pageSize 不能为空")
    @Min(value = 1, message = "pageSize 必须大于0")
    private Integer pageSize;

    /**
     * 排序规则列表
     */
    private List<OrderDTO> orders;

    /**
     * 排序规则
     */
    @Data
    public static class OrderDTO {
        /**
         * 排序字段名
         */
        @NotEmpty(message = "排序字段名不能为空")
        private String column;

        /**
         * 排序方向：ASC升序，DESC降序
         */
        @NotEmpty(message = "排序方向不能为空")
        private String direction;
    }

    public <T> Page<T> toMpPage() {
        Page<T> page = new Page<>(pageNum, pageSize);
        if (CollectionUtils.isNotEmpty(orders)) {
            page.setOrders(orders.stream().map(order -> {
                        OrderItem orderItem = new OrderItem();
                        orderItem.setColumn(order.getColumn());
                        orderItem.setAsc("ASC".equalsIgnoreCase(order.getDirection()));
                        return orderItem;
                    }
            ).toList());
        }
        return page;
    }
}
