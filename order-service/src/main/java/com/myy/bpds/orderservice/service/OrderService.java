package com.myy.bpds.orderservice.service;

import com.myy.bpds.orderservice.dto.CreateOrderRequest;
import com.myy.bpds.orderservice.dto.OrderResponse;

/**
 * 订单Service
 */
public interface OrderService {

    /**
     * 创建订单
     */
    OrderResponse createOrder(CreateOrderRequest request);
}
