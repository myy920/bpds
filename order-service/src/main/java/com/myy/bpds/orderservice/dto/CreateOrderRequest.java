package com.myy.bpds.orderservice.dto;

import lombok.Data;

/**
 * 创建订单请求DTO
 */
@Data
public class CreateOrderRequest {
    /**
     * 备注
     */
    private String remark;
}
