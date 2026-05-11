package com.myy.bpds.cartservice.constants;

import com.myy.bpds.common.constants.ErrorCode;
import lombok.Getter;

/**
 * 购物车业务错误码
 */
@Getter
public enum CartErrorCode implements ErrorCode {

    // 购物车业务错误码（50301-50399）
    CART_ITEM_NOT_EXIST(50301, "购物车项不存在"),
    CART_CREATE_FAILED(50302, "购物车创建失败"),
    CART_ADD_FAILED(50303, "添加到购物车失败"),
    CART_UPDATE_FAILED(50304, "购物车更新失败"),
    CART_DELETE_FAILED(50305, "购物车删除失败"),
    ;

    private final Integer code;
    private final String message;

    CartErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
