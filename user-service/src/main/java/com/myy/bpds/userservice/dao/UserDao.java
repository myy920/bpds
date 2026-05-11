package com.myy.bpds.userservice.dao;

import com.myy.bpds.common.mapper.LambdaBaseMapper;
import com.myy.bpds.userservice.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Mapper
 */
@Mapper
public interface UserDao extends LambdaBaseMapper<UserEntity> {
}
