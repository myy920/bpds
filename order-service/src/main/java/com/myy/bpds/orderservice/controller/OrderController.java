package com.myy.bpds.orderservice.controller;

import com.myy.bpds.common.dto.PageQuery;
import com.myy.bpds.common.dto.PageResult;
import com.myy.bpds.common.dto.Result;
import com.myy.bpds.orderservice.dto.CreateOrderRequest;
import com.myy.bpds.orderservice.dto.OrderResponse;
import com.myy.bpds.orderservice.entity.OrderEntity;
import com.myy.bpds.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 订单Controller
 */
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Validated
public class OrderController {

    private final OrderService orderService;

    /**
     * 创建订单
     */
    @PostMapping
    public Result<OrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        return Result.ok(orderService.createOrder(request));
    }

    /**
     * 分页查询订单
     *
     * @param pageQuery
     * @return
     */
    @PostMapping("/page")
    public Result<PageResult<OrderEntity>> pageOrders(@Valid @RequestBody PageQuery pageQuery) {
        return Result.ok(orderService.pageOrders(pageQuery));
    }
}
