package com.myy.bpds.cart.mapper;

import com.myy.bpds.common.mapper.BaseMapperPlus3;
import com.myy.bpds.cart.entity.CartEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 购物车Mapper
 */
@Mapper
public interface CartMapper extends BaseMapperPlus3<CartEntity> {
}