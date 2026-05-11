package com.myy.bpds.cartservice.dao;

import com.myy.bpds.cartservice.entity.CartEntity;
import com.myy.bpds.common.mapper.LambdaBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 购物车Mapper
 */
@Mapper
public interface CartDao extends LambdaBaseMapper<CartEntity> {
}