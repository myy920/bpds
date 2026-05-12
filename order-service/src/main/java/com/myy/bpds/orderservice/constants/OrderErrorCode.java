package com.myy.bpds.orderservice.constants;

import com.myy.bpds.common.constants.ErrorCode;
import lombok.Getter;

/**
 * 订单业务错误码
 */
@Getter
public enum OrderErrorCode implements ErrorCode {

    // 订单业务错误码（50401-50499）
    ORDER_CREATE_FAILED(50401, "订单创建失败"),
    ORDER_NOT_EXIST(50402, "订单不存在"),
    INSUFFICIENT_STOCK(50403, "库存不足"),
    CART_EMPTY(50404, "购物车为空"),
    ;

    private final Integer code;
    private final String message;

    OrderErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
