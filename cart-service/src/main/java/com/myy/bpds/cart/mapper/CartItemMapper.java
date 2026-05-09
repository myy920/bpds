package com.myy.bpds.cart.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myy.bpds.cart.entity.CartItemEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 购物车项Mapper
 */
@Mapper
public interface CartItemMapper extends BaseMapper<CartItemEntity> {
}
