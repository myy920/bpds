package com.myy.bpds.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, ProjectEntity>
        implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(ProjectEntity project) {
        saveBatch(null);

        // 校验项目名称是否已存在
        if (projectMapper.existsName(project.getName(), null)) {
            throw new BpdsException(ProjectErrorCode.PROJECT_ALREADY_EXIST, project.getName());
        }
        project.setCreateTime(LocalDateTime.now());
        project.setUpdateTime(LocalDateTime.now());
        projectMapper.insert(project);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        projectMapper.deleteById(id);
    }

    @Override
    @Transactional
    public int update(ProjectEntity project) {
        // 校验项目名称是否已存在（排除自己）
        if (projectMapper.existsName(project.getName(), project.getId())) {
            throw new BpdsException(ProjectErrorCode.PROJECT_ALREADY_EXIST, project.getName());
        }
        project.setUpdateTime(LocalDateTime.now());
        return projectMapper.updateById(project);
    }

    @Override
    public ProjectEntity selectById(String id) {
        return projectMapper.selectById(id);
    }

    @Override
    public List<ProjectEntity> selectAll() {
        return projectMapper.selectList(null);
    }

    @Override
    public List<ProjectEntity> selectByName(String name) {
        List<ProjectEntity> entities = projectMapper.selectList(null);
        projectMapper.insertOrUpdate(entities);


        return projectMapper.selectByName(name);
    }
}
