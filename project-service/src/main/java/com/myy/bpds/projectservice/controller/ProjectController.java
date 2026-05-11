package com.myy.bpds.projectservice.controller;

import com.myy.bpds.common.dto.PageQuery;
import com.myy.bpds.common.dto.PageResult;
import com.myy.bpds.common.dto.Result;
import com.myy.bpds.projectservice.entity.ProjectEntity;
import com.myy.bpds.projectservice.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目管理 Controller
 */
@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    /**
     * 新增项目
     *
     * @param project 项目实体
     * @return 新增结果
     */
    @PostMapping
    public Result<ProjectEntity> insert(@RequestBody ProjectEntity project) {
        projectService.insert(project);
        return Result.ok("新增成功", project);
    }

    /**
     * 根据 ID 删除项目
     *
     * @param id 项目ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteById(@PathVariable String id) {
        projectService.deleteById(id);
        return Result.ok();
    }

    /**
     * 更新项目
     *
     * @param project 项目实体
     * @return 更新结果
     */
    @PutMapping
    public Result<ProjectEntity> update(@RequestBody ProjectEntity project) {
        projectService.update(project);
        return Result.ok("更新成功", project);
    }

    /**
     * 根据 ID 查询项目
     *
     * @param id 项目ID
     * @return 项目实体
     */
    @GetMapping("/{id}")
    public Result<ProjectEntity> selectById(@PathVariable String id) {
        ProjectEntity project = projectService.selectById(id);
        return Result.ok(project);
    }

    /**
     * 查询所有项目
     *
     * @return 项目列表
     */
    @GetMapping
    public Result<List<ProjectEntity>> selectAll() {
        List<ProjectEntity> projects = projectService.selectAll();
        return Result.ok(projects);
    }

    /**
     * 分页查询项目
     *
     * @param pageQuery 分页查询参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public Result<PageResult<ProjectEntity>> page(@RequestBody PageQuery pageQuery) {
        return Result.ok(projectService.page(pageQuery));
    }

    /**
     * 根据名称模糊查询项目
     *
     * @param name 项目名称
     * @return 项目列表
     */
    @GetMapping("/search")
    public Result<List<ProjectEntity>> selectByName(@RequestParam String name) {
        List<ProjectEntity> projects = projectService.selectByName(name);
        return Result.ok(projects);
    }
}
