package com.myy.bpds.cartservice.dao;

import com.myy.bpds.cartservice.entity.CartItemEntity;
import com.myy.bpds.common.mybatisplus.LambdaBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 购物车项Mapper
 */
@Mapper
public interface CartItemDao extends LambdaBaseMapper<CartItemEntity> {
}
