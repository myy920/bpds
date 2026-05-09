package com.myy.bpds.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * 项目实体类
 */
@Getter
@Setter
@TableName("t_project")
public class ProjectEntity extends BaseEntity {
    /**
     * 项目ID（主键）
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
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
