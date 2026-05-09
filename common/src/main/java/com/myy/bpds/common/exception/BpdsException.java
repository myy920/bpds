package com.myy.bpds.common.exception;

import com.myy.bpds.common.constants.BasicErrorCode;
import com.myy.bpds.common.constants.ErrorCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 业务异常类
 */
@Getter
@Setter
public class BpdsException extends RuntimeException {
    private final Integer code;
    private final String message;

    public BpdsException(String message) {
        super(message);
        this.code = BasicErrorCode.INTERNAL_SERVER_ERROR.getCode();
        this.message = message;
    }

    public BpdsException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BpdsException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public BpdsException(ErrorCode errorCode, String appendMessage) {
        super(appendMessage);
        this.code = errorCode.getCode();
        this.message = String.format("%s: %s", errorCode.getMessage(), appendMessage);
    }
}
