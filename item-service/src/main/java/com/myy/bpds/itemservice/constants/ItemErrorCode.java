package com.myy.bpds.itemservice.constants;

import com.myy.bpds.common.constants.ErrorCode;
import lombok.Getter;

/**
 * 商品业务错误码
 */
@Getter
public enum ItemErrorCode implements ErrorCode {

    // 商品业务错误码（50201-50299）
    ITEM_NOT_EXIST(50201, "商品不存在"),
    ITEM_STOCK_INSUFFICIENT(50202, "商品库存不足"),
    ITEM_CREATE_FAILED(50203, "商品创建失败"),
    ITEM_UPDATE_FAILED(50204, "商品更新失败"),
    ITEM_DELETE_FAILED(50205, "商品删除失败"),
    ;

    private final Integer code;
    private final String message;

    ItemErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
