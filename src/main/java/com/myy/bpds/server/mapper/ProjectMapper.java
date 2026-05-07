package com.myy.bpds.server.mapper;

import com.myy.bpds.server.entity.ProjectEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {

    int insert(ProjectEntity project);

    int deleteById(String id);

    int update(ProjectEntity project);

    ProjectEntity selectById(String id);

    List<ProjectEntity> selectAll();

    List<ProjectEntity> selectByName(String name);
}
