package com.myy.bpds.server.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myy.bpds.server.entity.ProjectEntity;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 项目 Mapper 接口
 */
@Mapper
public interface ProjectMapper extends BaseMapper<ProjectEntity> {

    /**
     * 根据名称查询项目列表
     */
    List<ProjectEntity> selectByName(@Param("name") String name);

    default boolean existsName(String name, @Nullable String id) {
        LambdaQueryWrapper<ProjectEntity> wrapper = new LambdaQueryWrapper<ProjectEntity>()
                .eq(ProjectEntity::getName, name)
                .ne(StringUtils.isNotEmpty(id), ProjectEntity::getId, id);
        return selectCount(wrapper) > 0;
    }
}
