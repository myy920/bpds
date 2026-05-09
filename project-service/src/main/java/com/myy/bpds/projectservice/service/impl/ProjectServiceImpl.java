package com.myy.bpds.projectservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myy.bpds.common.dto.PageQuery;
import com.myy.bpds.common.dto.PageResult;
import com.myy.bpds.projectservice.entity.ProjectEntity;
import com.myy.bpds.projectservice.service.ProjectService;
import com.myy.bpds.projectservice.mapper.ProjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Administrator
* @description 针对表【t_project(项目表)】的数据库操作Service实现
* @createDate 2026-05-09 21:04:44
*/
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, ProjectEntity>
    implements ProjectService{

    @Override
    public void insert(ProjectEntity project) {

    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public int update(ProjectEntity project) {
        return 0;
    }

    @Override
    public ProjectEntity selectById(String id) {
        return null;
    }

    @Override
    public List<ProjectEntity> selectAll() {
        return List.of();
    }

    @Override
    public PageResult<ProjectEntity> page(PageQuery pageQuery) {
        return null;
    }

    @Override
    public List<ProjectEntity> selectByName(String name) {
        return List.of();
    }
}




