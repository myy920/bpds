package com.myy.bpds.common.constants;

import lombok.Getter;

/**
 * 统一响应状态码枚举
 */
@Getter
public enum BasicCode implements ResultCode {

    // 成功
    SUCCESS(200, "ok"),

    // 客户端错误
    BAD_REQUEST(400, "请求参数错误"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源不存在"),
    METHOD_NOT_ALLOWED(405, "请求方法不允许"),
    CONFLICT(409, "资源冲突"),

    // 服务器错误
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    SERVICE_UNAVAILABLE(503, "服务不可用"),
    ;

    private final Integer code;
    private final String message;

    BasicCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
