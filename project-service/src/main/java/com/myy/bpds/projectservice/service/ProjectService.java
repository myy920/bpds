package com.myy.bpds.projectservice.service;

import com.myy.bpds.common.dto.PageQuery;
import com.myy.bpds.common.dto.PageResult;
import com.myy.bpds.projectservice.entity.ProjectEntity;

import java.util.List;

/**
 * 项目服务接口
 */
public interface ProjectService {

    /**
     * 新增项目
     *
     * @param project 项目实体
     */
    void insert(ProjectEntity project);

    /**
     * 根据ID删除项目
     *
     * @param id 项目ID
     */
    void deleteById(String id);

    /**
     * 更新项目
     *
     * @param project 项目实体
     * @return 更新结果
     */
    int update(ProjectEntity project);

    /**
     * 根据ID查询项目
     *
     * @param id 项目ID
     * @return 项目实体
     */
    ProjectEntity selectById(String id);

    /**
     * 查询所有项目
     *
     * @return 项目列表
     */
    List<ProjectEntity> selectAll();

    /**
     * 分页查询项目
     *
     * @param pageQuery 分页查询参数
     * @return 分页结果
     */
    PageResult<ProjectEntity> page(PageQuery pageQuery);

    /**
     * 根据名称模糊查询项目
     *
     * @param name 项目名称
     * @return 项目列表
     */
    List<ProjectEntity> selectByName(String name);
}
