package com.myy.bpds.server.service.impl;

import com.myy.bpds.common.constants.ProjectErrorCode;
import com.myy.bpds.common.exception.BpdsException;
import com.myy.bpds.server.entity.ProjectEntity;
import com.myy.bpds.server.mapper.ProjectMapper;
import com.myy.bpds.server.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(ProjectEntity project) {
        // 校验项目名称是否已存在
        List<ProjectEntity> existingProjects = projectMapper.selectByName(project.getName());
        if (!existingProjects.isEmpty()) {
            throw new BpdsException(ProjectErrorCode.PROJECT_ALREADY_EXIST);
        }
        project.setId(UUID.randomUUID().toString().replace("-", ""));
        project.setCreateTime(LocalDateTime.now());
        project.setUpdateTime(LocalDateTime.now());
        projectMapper.insert(project);
    }

    @Override
    public void deleteById(String id) {
        projectMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(ProjectEntity project) {
        // 校验项目名称是否已存在（排除自己）
        List<ProjectEntity> existingProjects = projectMapper.selectByName(project.getName());
        if (!existingProjects.isEmpty()) {
            // 如果查询到的项目不是当前项目，则说明重名
            if (existingProjects.stream().noneMatch(p -> p.getId().equals(project.getId()))) {
                throw new BpdsException(ProjectErrorCode.PROJECT_ALREADY_EXIST);
            }
        }

        project.setUpdateTime(LocalDateTime.now());
        return projectMapper.update(project);
    }

    @Override
    public ProjectEntity selectById(String id) {
        return projectMapper.selectById(id);
    }

    @Override
    public List<ProjectEntity> selectAll() {
        return projectMapper.selectAll();
    }

    @Override
    public List<ProjectEntity> selectByName(String name) {
        return projectMapper.selectByName(name);
    }
}
