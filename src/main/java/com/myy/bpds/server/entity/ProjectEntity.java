package com.myy.bpds.server.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ProjectEntity {

    private String id;

    private String name;

    private String description;

    private String creator;

    private LocalDateTime createTime;

    private String updater;

    private LocalDateTime updateTime;
}
