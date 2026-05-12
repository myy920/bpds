package com.myy.bpds.common.constants;

/**
 * 统一响应状态码枚举
 */
public interface ErrorCode {
    Integer getCode();

    String getMessage();

    default boolean isError() {
        return getCode() != 200;
    }
}
