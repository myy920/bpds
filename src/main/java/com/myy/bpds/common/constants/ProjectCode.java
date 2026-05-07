

package com.myy.bpds.common.constants;

import lombok.Getter;

@Getter
public enum ProjectCode implements ResultCode {

    // 项目业务错误码（50101-50199）
    PROJECT_NOT_EXIST(50101, "项目不存在"),
    PROJECT_ALREADY_EXIST(50102, "项目已存在"),
    PROJECT_NAME_INVALID(50103, "项目名称不合法"),
    PROJECT_CREATE_FAILED(50104, "项目创建失败"),
    PROJECT_UPDATE_FAILED(50105, "项目更新失败"),
    PROJECT_DELETE_FAILED(50106, "项目删除失败"),
    PROJECT_QUERY_FAILED(50107, "项目查询失败"),
    ;

    private final Integer code;
    private final String message;

    ProjectCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
