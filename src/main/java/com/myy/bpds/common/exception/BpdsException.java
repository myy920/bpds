package com.myy.bpds.common.exception;

import com.myy.bpds.common.constants.BasicCode;
import com.myy.bpds.common.constants.ResultCode;
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
        this.code = BasicCode.INTERNAL_SERVER_ERROR.getCode();
        this.message = message;
    }

    public BpdsException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BpdsException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public BpdsException(ResultCode resultCode, String message) {
        super(message);
        this.code = resultCode.getCode();
        this.message = message;
    }
}
