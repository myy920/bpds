package com.myy.bpds.userservice.constants;

import com.myy.bpds.common.constants.ErrorCode;
import lombok.Getter;

/**
 * 用户服务错误码
 */
@Getter
public enum UserErrorCode implements ErrorCode {

    // 用户业务错误码（50201-50299）
    USER_NOT_FOUND(50201, "用户不存在"),
    USER_ALREADY_EXISTS(50202, "用户已存在"),
    PASSWORD_ERROR(50203, "账号或密码错误"),
    USER_DISABLED(50204, "用户已被禁用"),
    USERNAME_OR_PASSWORD_EMPTY(50205, "账号和密码不能为空"),
    ;

    private final Integer code;
    private final String message;

    UserErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
