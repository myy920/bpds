package com.myy.bpds.userservice.mapper;

import com.myy.bpds.common.mapper.LambdaBaseMapper;
import com.myy.bpds.userservice.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Mapper
 */
@Mapper
public interface UserMapper extends LambdaBaseMapper<UserEntity> {
}
