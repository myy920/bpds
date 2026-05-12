package com.myy.bpds.orderservice.controller;

import com.myy.bpds.common.dto.Result;
import com.myy.bpds.orderservice.dto.CreateOrderRequest;
import com.myy.bpds.orderservice.dto.OrderResponse;
import com.myy.bpds.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 订单Controller
 */
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * 创建订单
     */
    @PostMapping
    public Result<OrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        return Result.ok(orderService.createOrder(request));
    }
}
