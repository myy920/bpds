package com.myy.bpds.orderservice.dto;

import com.myy.bpds.orderservice.entity.OrderEntity;
import lombok.Data;

/**
 * 订单响应DTO
 */
@Data
public class OrderResponse {
    /**
     * 订单信息
     */
    private OrderEntity order;
}
