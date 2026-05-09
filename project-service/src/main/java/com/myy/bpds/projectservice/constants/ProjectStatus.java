package com.myy.bpds.projectservice.constants;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum ProjectStatus {
    DESIGN(0, "设计"),
    DEVELOPING(1, "开发中"),
    TESTING(2, "测试中"),
    ONLINE(3, "已上线"),
    OFFLINE(4, "已下线");

    @EnumValue
    private final int value;
    private final String name;

    ProjectStatus(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
