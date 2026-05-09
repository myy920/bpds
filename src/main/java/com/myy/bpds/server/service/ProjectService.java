package com.myy.bpds.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myy.bpds.server.entity.ProjectEntity;

import java.util.List;

public interface ProjectService extends IService<ProjectEntity> {

    /**
     * 新增项目
     */
    void insert(ProjectEntity project);

    /**
     * 根据ID删除项目
     */
    void deleteById(String id);

    /**
     * 更新项目
     */
    int update(ProjectEntity project);

    /**
     * 根据ID查询项目
     */
    ProjectEntity selectById(String id);

    /**
     * 查询所有项目
     */
    List<ProjectEntity> selectAll();

    /**
     * 根据名称模糊查询项目
     */
    List<ProjectEntity> selectByName(String name);
}
