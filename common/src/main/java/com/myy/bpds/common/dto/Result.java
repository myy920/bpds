package com.myy.bpds.common.dto;

import com.myy.bpds.common.constants.BasicErrorCode;
import com.myy.bpds.common.constants.ErrorCode;
import lombok.Data;

import java.util.Objects;

/**
 * 统一响应结果类
 */
@Data
public class Result<T> {

    private Integer code;
    private String message;
    private T data;

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isError() {
        return !Objects.equals(code, BasicErrorCode.SUCCESS.getCode());
    }

    /**
     * 成功响应（无数据）
     */
    public static <T> Result<T> ok() {
        return new Result<>(BasicErrorCode.SUCCESS.getCode(), BasicErrorCode.SUCCESS.getMessage(), null);
    }

    /**
     * 成功响应（带数据）
     */
    public static <T> Result<T> ok(T data) {
        return new Result<>(BasicErrorCode.SUCCESS.getCode(), BasicErrorCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功响应（自定义消息和数据）
     */
    public static <T> Result<T> ok(String message, T data) {
        return new Result<>(BasicErrorCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 成功响应（使用状态码枚举）
     */
    public static <T> Result<T> ok(ErrorCode errorCode, T data) {
        return new Result<>(errorCode.getCode(), errorCode.getMessage(), data);
    }

    /**
     * 失败响应
     */
    public static <T> Result<T> error(String message) {
        return new Result<>(BasicErrorCode.INTERNAL_SERVER_ERROR.getCode(), message, null);
    }

    /**
     * 失败响应（自定义状态码）
     */
    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(code, message, null);
    }

    /**
     * 失败响应（使用状态码枚举）
     */
    public static <T> Result<T> error(ErrorCode errorCode) {
        return new Result<>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败响应（使用状态码枚举，自定义消息）
     */
    public static <T> Result<T> error(ErrorCode errorCode, String message) {
        return new Result<>(errorCode.getCode(), message, null);
    }
}
