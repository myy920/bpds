package com.myy.bpds.cartservice.mapper;

import com.myy.bpds.cartservice.entity.CartItemEntity;
import com.myy.bpds.common.mapper.LambdaBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 购物车项Mapper
 */
@Mapper
public interface CartItemMapper extends LambdaBaseMapper<CartItemEntity> {
}
