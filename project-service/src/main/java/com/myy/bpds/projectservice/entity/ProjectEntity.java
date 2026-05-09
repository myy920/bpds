package com.myy.bpds.projectservice.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myy.bpds.common.entity.BaseEntity;
import lombok.Data;

/**
 * 项目表
 *
 * @TableName t_project
 */
@TableName(value = "t_project")
@Data
public class ProjectEntity extends BaseEntity {
    /**
     * 项目ID
     */
    @TableId(value = "id")
    private String id;

    /**
     * 项目名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 项目描述
     */
    @TableField(value = "description")
    private String description;
}