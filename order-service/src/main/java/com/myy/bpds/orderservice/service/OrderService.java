package com.myy.bpds.orderservice.service;

import com.myy.bpds.common.dto.PageQuery;
import com.myy.bpds.common.dto.PageResult;
import com.myy.bpds.orderservice.dto.CreateOrderRequest;
import com.myy.bpds.orderservice.dto.OrderResponse;
import com.myy.bpds.orderservice.entity.OrderEntity;

/**
 * 订单Service
 */
public interface OrderService {

    /**
     * 创建订单
     */
    OrderResponse createOrder(CreateOrderRequest request);

    PageResult<OrderEntity> pageOrders(PageQuery pageQuery);
}
