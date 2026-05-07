package com.myy.bpds.server.controller;

import com.myy.bpds.common.dto.Result;
import com.myy.bpds.server.entity.ProjectEntity;
import com.myy.bpds.server.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目管理 Controller
 */
@RestController
@RequestMapping("/v1/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    /**
     * 新增项目
     */
    @PostMapping
    public Result<ProjectEntity> insert(@RequestBody ProjectEntity project) {
        projectService.insert(project);
        return Result.ok("新增成功", project);
    }

    /**
     * 根据 ID 删除项目
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteById(@PathVariable String id) {
        projectService.deleteById(id);
        return Result.ok();
    }

    /**
     * 更新项目
     */
    @PutMapping
    public Result<ProjectEntity> update(@RequestBody ProjectEntity project) {
        projectService.update(project);
        return Result.ok("更新成功", project);
    }

    /**
     * 根据 ID 查询项目
     */
    @GetMapping("/{id}")
    public Result<ProjectEntity> selectById(@PathVariable String id) {
        ProjectEntity project = projectService.selectById(id);
        return Result.ok(project);
    }

    /**
     * 查询所有项目
     */
    @GetMapping
    public Result<List<ProjectEntity>> selectAll() {
        List<ProjectEntity> projects = projectService.selectAll();
        return Result.ok(projects);
    }

    /**
     * 根据名称模糊查询项目
     */
    @GetMapping("/search")
    public Result<List<ProjectEntity>> selectByName(@RequestParam String name) {
        List<ProjectEntity> projects = projectService.selectByName(name);
        return Result.ok(projects);
    }
}
