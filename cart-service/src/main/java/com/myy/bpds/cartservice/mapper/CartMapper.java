package com.myy.bpds.cartservice.mapper;

import com.myy.bpds.common.mapper.LambdaBaseMapper;
import com.myy.bpds.cartservice.entity.CartEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 购物车Mapper
 */
@Mapper
public interface CartMapper extends LambdaBaseMapper<CartEntity> {
}